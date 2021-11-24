package com.example.demo;

import com.example.demo.beans.FileWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ModuleWorker {
    @Autowired
    private App app;

    public List<FileWorker> getModules(String path){
        var ctx = app.getContext();
        List<FileWorker> modules = new ArrayList<>();

        for (String e : ctx.getBeanDefinitionNames()){
            Object bean = ctx.getBean(e);

            FileWorker module;
            try {
                module = (FileWorker) bean;
            } catch (Exception ex) {
                continue;
            }

            if(module.isSupportedFile(path)){
                modules.add(module);
            }
        }
        return modules;
    }
}
