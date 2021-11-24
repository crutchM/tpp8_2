package com.example.demo.beans.cfg;

import com.example.demo.ModuleWorker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.example.demo")
public class Config {

    @Bean(name = "worker")
    public ModuleWorker provideModuleWorker(){
        return new ModuleWorker();
    }
}
