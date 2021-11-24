package com.example.demo.beans;

import java.io.PrintStream;

public interface FileWorker {
    boolean isSupportedFile(String path);
    String getFuncDescription();
    void execute(String path, PrintStream writer);
}
