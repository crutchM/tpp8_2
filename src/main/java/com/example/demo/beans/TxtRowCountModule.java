package com.example.demo.beans;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
@Component
public class TxtRowCountModule implements FileWorker {
    @Override
    public boolean isSupportedFile(String path) {
        return path != null && path.contains(".") &&
                path.split("\\.")[path.split("\\.").length - 1].equals("txt");
    }

    @Override
    public String getFuncDescription() {
        return "print count of rows in txt file";
    }

    @Override
    public void execute(String path, PrintStream writer) {
        try {
            if(!isSupportedFile(path))
                throw new IOException("invalid file");
            List<String> lines = new ArrayList<>();
            try (Stream<String> st = Files.lines(Paths.get(path))){
                st.forEach(lines::add);
            }
            writer.println(path + " contains" + lines.size() + " lines");
        } catch (Exception ex){
            writer.println("error : failed ro process file: \\n\\t\\t\\t" + ex.getLocalizedMessage() + " lines0" );
            ex.printStackTrace();
        }
    }
}
