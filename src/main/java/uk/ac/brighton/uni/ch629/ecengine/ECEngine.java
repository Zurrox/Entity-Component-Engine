package uk.ac.brighton.uni.ch629.ecengine;

import javax.swing.*;

public class ECEngine {
    public static void main(String[] args) {
        JFrame frame = new JFrame("ECEngine Test");
        frame.setBounds(0, 0, 1024, 768);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void start() {

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