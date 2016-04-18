package uk.ac.brighton.uni.ch629.ecengine.misc;

import uk.ac.brighton.uni.ch629.ecengine.event.EventBus;
import uk.ac.brighton.uni.ch629.ecengine.event.KeyPressEvent;
import uk.ac.brighton.uni.ch629.ecengine.event.KeyReleaseEvent;

import java.util.*;

public class Keyboard {
    private static final Set<Integer> keysDown = new HashSet<Integer>();

    public static void pressKey(int keyCode, EventBus bus) {
        //TODO: Figure a way out to implement the EventBus into this without needing it every time, maybe a new Keyboard controller for each GameWindow(Which would probably be right...)
        if(!keysDown.contains(keyCode)) {
            keysDown.add(keyCode);
            bus.send(new KeyPressEvent(keyCode));
        }
    }

    public static void releaseKey(int keyCode, EventBus bus) {
        if(keysDown.remove(keyCode) && bus != null) {
            bus.send(new KeyReleaseEvent(keyCode));
        }
    }

    public static Set<Integer> getKeysDown() {
        return keysDown;
    }

    public static boolean isKeyDown(int code) {
        return keysDown.contains(code);
    }
}