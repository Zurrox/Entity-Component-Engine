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

    public void update(double deltaTime) {

    }

    public void render(Graphics g) {
    }

    public void initialize() {

    }
}
//TODO: Game States (Main menu state, game state etc)
//TODO: Rendering Shapes
//TODO: Graphics class allowing access directly to drawing to the Window
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
//TODO: Find some elegant way to create blueprint entities & also create any entity with some set values (Right now need to define either all as defaults or all as non-defaults)
//TODO: Component Dependencies (Box Collider requires Transform)
//TODO: Make all Updates use Double
//TODO: Overlays? Pause Menu -> Needs to pause main game when opened.
//TODO: Update Systems to use AWT/Swing's shapes, like Oval and Rectangle rather than Box2i and Circl2i