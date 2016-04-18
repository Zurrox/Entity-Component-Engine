package uk.ac.brighton.uni.ch629.ecengine.event;

public class KeyEvent implements IEvent {
    public int keyCode;

    public KeyEvent(final int keyCode) {
        this.keyCode = keyCode;
    }
}