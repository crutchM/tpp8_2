package com.example.demo.beans;

import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

@Component
public class PngResModule  implements FileWorker{
    @Override
    public boolean isSupportedFile(String path) {
        return path != null && path.contains(".") && path.split("\\.")[path.split("\\.").length - 1].equals("png");
    }

    @Override
    public String getFuncDescription() {
        return "print resolution of png file";
    }

    @Override
    public void execute(String path, PrintStream writer) {
        try {
            if(!isSupportedFile(path)){
                throw new IOException("invalid file");
            }
            BufferedImage image = ImageIO.read(new File(path));
            writer.println(path + " has resolution: " + image.getWidth() + "X" + image.getHeight());
        } catch (Exception e){
            writer.println("error : Failed to process file:\n\t\t\t" + e.getLocalizedMessage() + " lines");
            e.printStackTrace(writer);
        }
    }
}
