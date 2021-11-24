package com.example.demo;

import com.example.demo.beans.FileWorker;
import com.example.demo.beans.cfg.Config;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
@Service
public class App {
    private static final App app = new App();
    private static final ApplicationContext ctx =new AnnotationConfigApplicationContext(Config.class);

    public static void main(String args[]){
        if(args == null || args.length == 0){
            System.out.println("no args");
            return;
        }
        PrintStream out = System.out;
        var path = args[0];
        var context = app.getContext();
        context.getBean("worker", ModuleWorker.class).getModules(path);
        List<FileWorker> fileWorkerList = app.getContext().getBean("worker", ModuleWorker.class).getModules(path);
        performModules(out, fileWorkerList, path);
    }

    private static void performModules(PrintStream out, List<FileWorker> fileWorkerList, String path){
        out.println("found" + fileWorkerList.size() + "modules for your file");
        for (FileWorker f : fileWorkerList){
            out.println(f.getClass().getSimpleName());
        }

        if(fileWorkerList.size() == 0){
            return;
        }

        var i = -1;
        while (i < 0){
            out.println("select modules");
            try {
                int opt = new Scanner(System.in).nextInt();
                i = opt > 0 && opt <= fileWorkerList.size() ? opt - 1 : i;

            } catch (Exception e){
                out.println("try again");
            }
        }
    }

    public ApplicationContext getContext(){
        return ctx;
    }

    private static void perform(String path, FileWorker module, PrintStream out){
        out.println(module.getFuncDescription());
        module.execute(path, out);
    }
}
