package uk.ac.brighton.uni.ch629.ecengine.game;

import uk.ac.brighton.uni.ch629.ecengine.entity.Entity;
import uk.ac.brighton.uni.ch629.ecengine.event.EventBus;
import uk.ac.brighton.uni.ch629.ecengine.event.MouseClickEvent;
import uk.ac.brighton.uni.ch629.ecengine.event.MouseScrollEvent;
import uk.ac.brighton.uni.ch629.ecengine.logic.Timer;
import uk.ac.brighton.uni.ch629.ecengine.logic.World;
import uk.ac.brighton.uni.ch629.ecengine.misc.Keyboard;
import uk.ac.brighton.uni.ch629.ecengine.misc.Mouse;
import uk.ac.brighton.uni.ch629.ecengine.misc.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

/**
 * The Window for the game to be contained within.
 */
public abstract class GameWindow extends JFrame {
    public final HashMap<String, GameState> STATE_LIST;
    public final Timer TIMER;
    public final Settings SETTINGS;
    /**
     * The Keyboard Handler for this Window
     */
    public final Keyboard KEYBOARD;
    /**
     * The Mouse Handler for this Window
     */
    public final Mouse MOUSE;
    public final List<World> WORLDS;
    public int currentState = 0;
    public World currentWorld; //TODO: Maybe hold GameState and just have a reference to World through that(GameWindow.getCurrentWorld())
    private Image offBuffer = null;

    /**
     * @param title The Title of the Window
     * @param width The Width of the Window
     * @param height The Height of the Window
     */
    public GameWindow(String title, int width, int height) {
        super(title);
        setContentPane(new JPanel());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(width, height);
        setVisible(true);

        final EventBus eventBus = new EventBus();
        SETTINGS = new Settings();
        STATE_LIST = new HashMap<>();
        TIMER = new Timer();
        KEYBOARD = new Keyboard(eventBus);
        MOUSE = new Mouse();
        WORLDS = new ArrayList<>();

        SETTINGS.setSetting("resources", "D:\\Programming\\Entity Component Engine\\Resources");

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
                eventBus.sendNow(new MouseClickEvent(getMousePosition(), e.getButton()));
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

        addMouseWheelListener(e -> eventBus.sendNow(new MouseScrollEvent(e.getPoint(), e.getWheelRotation())));

        initialize();

        //TODO: Threading. Game loop needs to be in own thread, and events need to be separated

        Thread thread = new Thread(() -> {
            javax.swing.Timer tim = new javax.swing.Timer(1000 / 60, e -> {
                offBuffer = createImage(getWidth(), getHeight());
                update(TIMER.getDeltaTimeMilli());
                currentWorld.EVENT_BUS.sendQueued();
                killEntities();
                currentWorld.addAllEntities();
                currentWorld.COLLISION_HANDLER.addAllColliders();
                render((Graphics2D) offBuffer.getGraphics());
                getCurrentBuffer().drawImage(offBuffer, 0, 0, getContentPane());
            });
            tim.start();
        });
        thread.start();
    }

    public void killEntities() {
        /*synchronized (currentWorld.entities) {
            for (Iterator<Map.Entry<UUID, Entity>> it = currentWorld.entities.entrySet().iterator(); it.hasNext(); ) {
                Map.Entry<UUID, Entity> value = it.next();
                if (value.getValue().dead) {
                    value.getValue().kill();
                    it.remove();
                }
            }
        }*/

        Iterator<Entity> itr = currentWorld.entities.values().iterator();
        while(itr.hasNext()) {
            Entity entity = itr.next();
            if(entity.dead) {
                entity.kill();
                itr.remove();
            }
        }
    }

    private Graphics getCurrentBuffer() {
        return getContentPane().getGraphics();
    }

    public void addState(String name, GameState state) { //TODO: Maybe use a name reference to the GameState
        STATE_LIST.put(name, state);
        state.initialize();
    }

    public void removeState(String name) {
        STATE_LIST.remove(name);
    }

    public void loadState(String name) {
        //TODO: Implement
    }

    public GameState getState(String name) {
        return STATE_LIST.get(name);
    }

    public void update(double deltaTime) {
        GameState state = STATE_LIST.get(currentState);
        if (!state.isPaused()) state.update(deltaTime);
    }

    public void render(Graphics2D g) {
        GameState state = STATE_LIST.get(currentState);
        if (!state.isPaused()) state.render(g);
    }

    public abstract void initialize();
}