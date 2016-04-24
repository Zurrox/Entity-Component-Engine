package uk.ac.brighton.uni.ch629.ecengine.resources;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class ResourceManager { //TODO: Implement - Needs to load Images and Sound files to be played later on.
    HashMap<String, BufferedImage> images; //TODO: SOUNDS

    public ResourceManager(String resourceFolder) {
        images = new HashMap<>();

        Path imagePath = Paths.get(resourceFolder + "\\image");

        File imgFolder = imagePath.toFile();
        for (File file : imgFolder.listFiles()) {
            try {
                images.put(file.getName().split(".")[0], ImageIO.read(file)); //TODO: Could have a JSON file containing all Images, which has a name for each of them, could also hold animation details
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
