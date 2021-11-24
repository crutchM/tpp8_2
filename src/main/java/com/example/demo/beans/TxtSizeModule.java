package com.example.demo.beans;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class TxtSizeModule implements  FileWorker{

    @Override
    public boolean isSupportedFile(String path) {
        return path != null && path.contains(".") && path.split("\\.")[path.split("\\.").length - 1].equals("txt");
    }

    @Override
    public String getFuncDescription() {
        return "print size of txt file";
    }

    @Override
    public void execute(String path, PrintStream writer) {
        try {
            if(!isSupportedFile(path)){
                throw new IOException("invalid file");
            }
            writer.println("Size of " + path + ": " + Files.size(Path.of(path)));

        } catch (Exception e){
            writer.println("error :  Failed to get txt file size:\n\t\t\t" + e.getLocalizedMessage());
            e.printStackTrace(writer);
        }
    }
}
