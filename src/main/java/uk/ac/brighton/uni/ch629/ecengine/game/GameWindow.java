package uk.ac.brighton.uni.ch629.ecengine.game;

import uk.ac.brighton.uni.ch629.ecengine.TestListener;
import uk.ac.brighton.uni.ch629.ecengine.event.EventBus;
import uk.ac.brighton.uni.ch629.ecengine.misc.Keyboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameWindow extends JFrame {

    public GameWindow(String title, int width, int height) {
        super(title);
        setContentPane(new DrawPane());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(width, height);
        setVisible(true);

        final EventBus eventBus = new EventBus();

        TestListener tl = new TestListener(eventBus);

        addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
                Keyboard.pressKey(e.getKeyCode(), eventBus);
            }

            public void keyReleased(KeyEvent e) { //TODO: Keyboard Events? onKeyPress & onKeyRelease -> Need reference to an EventBus
                Keyboard.releaseKey(e.getKeyCode(), eventBus);
            }
        });

        addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {

            }

            public void mousePressed(MouseEvent e) {

            }

            public void mouseReleased(MouseEvent e) {

            }

            public void mouseEntered(MouseEvent e) {

            }

            public void mouseExited(MouseEvent e) {

            }
        });
    }
}

class DrawPane extends JPanel {
    @Override
    public void paintComponent(Graphics graphics) {
        graphics.fillRect(20, 20, 100, 200);
    }
}