package uk.ac.brighton.uni.ch629.ecengine;

import uk.ac.brighton.uni.ch629.ecengine.event.*;
import uk.ac.brighton.uni.ch629.ecengine.misc.Debug;

public class TestListener {
    private static SubscriptionClass<TestListener> subClass;

    public TestListener(EventBus eventBus) {
        subClass = eventBus.registerListenerClass(TestListener.class);
        subClass.subscribe(this);
    }

    @SubscribeEvent
    public void onKeyPress(KeyPressEvent event) {
        Debug.println("onKeyPress");
    }

    @SubscribeEvent
    public void onKeyRelease(KeyReleaseEvent event) {
        Debug.println("onKeyRelease");
    }

    @SubscribeEvent
    public void onKeyType(KeyTypeEvent event) {
        Debug.println("onKeyType");
    }

    @SubscribeEvent
    public void onClick(MouseClickEvent event) {
        Debug.println("Click @ " + event.pointClicked.toString());
    }

    @SubscribeEvent
    public void onScroll(MouseScrollEvent event) {
        Debug.println("Scroll: " + event.scroll);
    }
}