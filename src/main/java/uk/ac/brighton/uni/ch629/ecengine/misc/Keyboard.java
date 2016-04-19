package uk.ac.brighton.uni.ch629.ecengine.misc;

import uk.ac.brighton.uni.ch629.ecengine.event.EventBus;
import uk.ac.brighton.uni.ch629.ecengine.event.KeyPressEvent;
import uk.ac.brighton.uni.ch629.ecengine.event.KeyReleaseEvent;

import java.util.*;

public class Keyboard {
    private final Set<Integer> keysDown = new HashSet<Integer>();
    private final EventBus eventBus;

    public Keyboard(EventBus bus) {
        eventBus = bus;
    }

    public void pressKey(int keyCode) {
        if(!keysDown.contains(keyCode)) {
            keysDown.add(keyCode);
            eventBus.send(new KeyPressEvent(keyCode));
        }
    }

    public void releaseKey(int keyCode) {
        if(keysDown.remove(keyCode) && eventBus != null) {
            eventBus.send(new KeyReleaseEvent(keyCode));
        }
    }

    public Set<Integer> getKeysDown() {
        return keysDown;
    }

    public boolean isKeyDown(int code) {
        return keysDown.contains(code);
    }
}