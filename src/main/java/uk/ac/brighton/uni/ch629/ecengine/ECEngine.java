package uk.ac.brighton.uni.ch629.ecengine;

import uk.ac.brighton.uni.ch629.ecengine.game.GameWindow;

public class ECEngine {
    public static void main(String[] args) {
        new GameWindow("ECEngine Test", 1024, 768);
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