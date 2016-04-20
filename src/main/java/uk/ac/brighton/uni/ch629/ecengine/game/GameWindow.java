package uk.ac.brighton.uni.ch629.ecengine.game;

import uk.ac.brighton.uni.ch629.ecengine.TestListener;
import uk.ac.brighton.uni.ch629.ecengine.event.EventBus;
import uk.ac.brighton.uni.ch629.ecengine.event.MouseClickEvent;
import uk.ac.brighton.uni.ch629.ecengine.event.MouseScrollEvent;
import uk.ac.brighton.uni.ch629.ecengine.logic.*;
import uk.ac.brighton.uni.ch629.ecengine.logic.Timer;
import uk.ac.brighton.uni.ch629.ecengine.misc.Keyboard;
import uk.ac.brighton.uni.ch629.ecengine.misc.Mouse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The Window for the game to be contained within.
 */
public abstract class GameWindow extends JFrame {
    public final Timer TIMER;
    /**
     * The Keyboard Handler for this Window
     */
    public final Keyboard KEYBOARD;
    /**
     * The Mouse Handler for this Window
     */
    public final Mouse MOUSE;

    public final List<World> WORLDS;

    public World currentWorld; //TODO: Maybe hold GameState and just have a reference to World through that(GameWindow.getCurrentWorld())

    /**
     * @param title  - The Title of the Window
     * @param width  - The Width of the Window
     * @param height - The Height of the Window
     */
    public GameWindow(String title, int width, int height) {
        super(title);
        setContentPane(new DrawPane());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(width, height);
        setVisible(true);

        final EventBus eventBus = new EventBus();
        TIMER = new Timer();
        KEYBOARD = new Keyboard(eventBus);
        MOUSE = new Mouse();
        WORLDS = new ArrayList<World>();

        TestListener tl = new TestListener(eventBus);


        //TODO: Add the other Listener events & Maybe find a more elegant way to do this.
        addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
                KEYBOARD.pressKey(e.getKeyCode());
            }

            public void keyReleased(KeyEvent e) {
                KEYBOARD.releaseKey(e.getKeyCode());
            }
        });

        addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                eventBus.send(new MouseClickEvent(getMousePosition()));
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

        addMouseWheelListener(new MouseWheelListener() {
            public void mouseWheelMoved(MouseWheelEvent e) {
                eventBus.send(new MouseScrollEvent(e.getPoint(), e.getWheelRotation()));
            }
        });

        initialize();

        while(true) { //TODO: Threading. Game loop needs to be in own thread, and events need to be separated
            double deltaTime = TIMER.getDeltaTimeMilli();
            try {
                if(deltaTime < 1) {
                    Thread.sleep(1); //Avoids updating too fast
                    deltaTime += TIMER.getDeltaTimeMilli();
                }
                update(deltaTime);
                getContentPane().getGraphics().clearRect(0, 0, getWidth(), getHeight()); //TODO: Need to deal with this, makes boxes flash on screen.
                render(getContentPane().getGraphics());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public abstract void update(double deltaTime);

    public abstract void render(Graphics g);

    public abstract void initialize();
}

/**
 * The Pane within the JFrame to handle rendering objects
 */
class DrawPane extends JPanel {
    @Override
    public void paintComponent(Graphics graphics) {
        //TODO: Render all components.
    }
}