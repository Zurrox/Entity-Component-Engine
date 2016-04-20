package uk.ac.brighton.uni.ch629.ecengine;

import uk.ac.brighton.uni.ch629.ecengine.game.GameWindow;

import java.awt.*;

public class ECEngine extends GameWindow {
    public ECEngine() {
        super("ECEngine Test", 1024, 768);
    }

    public static void main(String[] args) {
        new ECEngine();
    }

    public void update(int deltaTime) {

    }

    public void render(Graphics g) {

    }

    public void initialize() {

    }
}
//TODO: Game States (Main menu state, game state etc)
//TODO: Rendering Shapes
//TODO: Graphics class allowing access directly to drawing to the Window
//TODO: Update loop && Render?
//TODO: Maybe something in JSON to automate creation of some Entities
//TODO: Maybe a Game class or something (GameWindow?) for games to use, provide an Update, Render & Initialize loop
//TODO: Catch duplicate events
//TODO: Multithreading with events
//TODO: Localization?
//TODO: Keycode to Name? Don't exactly need this as programmers can compare the event.keyCode to KeyCode.VK_W
//TODO: JavaDocs
//TODO: Networking
//TODO: Remove mainClass from Gradle when building.
//TODO: Maybe some sort of blueprint for Entities to be created using.