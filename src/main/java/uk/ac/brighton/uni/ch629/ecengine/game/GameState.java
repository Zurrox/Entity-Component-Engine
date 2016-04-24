package uk.ac.brighton.uni.ch629.ecengine.game;

import java.awt.*;

public abstract class GameState {
    private final GameWindow WINDOW;
    private boolean isPaused = false;

    public GameState(GameWindow window) {
        this.WINDOW = window;
    }

    public GameWindow getWindow() {
        return WINDOW;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void setPaused(boolean paused) {
        this.isPaused = paused;
    }

    public abstract void initialize();

    public abstract void update(double deltaTime);

    public abstract void render(Graphics graphics);
}