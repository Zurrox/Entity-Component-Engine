package uk.ac.brighton.uni.ch629.ecengine.event;

import java.awt.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class EventBus {
    //TODO: Cancelling Events
    //TODO: Priority Events
    //TODO: Maybe an Event Queue to avoid 2 CollisionEvents being sent on each collision -> Or just somehow block the next one being sent in the CollisionHandler.
    Map<Class, SubscriptionClass> subscribedClasses;

    public EventBus() {
        subscribedClasses = new HashMap<Class, SubscriptionClass>();
    }

    public <T> SubscriptionClass<T> registerListenerClass(Class<T> clazz) {
        if (!subscribedClasses.containsKey(clazz)) {
            SubscriptionClass<T> subscriptionClass = new SubscriptionClass<T>();
            for (Method mtd : clazz.getMethods()) {
                Annotation[] annotations = mtd.getDeclaredAnnotations();
                if (annotations != null &&
                        annotations.length > 0 &&
                        mtd.getParameterTypes().length == 1 &&
                        mtd.getParameterTypes()[0].getClass().isInstance(IEvent.class)) {
                    for (Annotation annotation : annotations) {
                        if (annotation.annotationType() == SubscribeEvent.class) {
                            Class<? extends IEvent> param = (Class<? extends IEvent>) mtd.getParameterTypes()[0];
                            subscriptionClass.addMethod(param, mtd);
                        }
                    }
                }
            }
            subscribedClasses.put(clazz, subscriptionClass);
            return subscriptionClass;
        }
        return subscribedClasses.get(clazz);
    }

    public void subscribeObject(Object o) {
        if (subscribedClasses.containsKey(o.getClass())) subscribedClasses.get(o.getClass()).subscribe(o);
    }

    public void subscribeEvent(Method mtd) {
        if (mtd.getDeclaredAnnotations().length > 0 &&
                mtd.getParameterTypes().length == 1 &&
                mtd.getDeclaredAnnotations()[0].getClass().isInstance(Event.class) &&
                subscribedClasses.containsKey(mtd.getDeclaringClass())) {
            for (Annotation annotation : mtd.getDeclaredAnnotations()) {
                if (annotation.annotationType() == SubscribeEvent.class) {
                    Class<? extends IEvent> param = (Class<? extends IEvent>) mtd.getParameterTypes()[0];
                    subscribedClasses.get(mtd.getDeclaringClass()).addMethod(param, mtd);
                }
            }
        }
    }

    public void unsubscribeObject(Object o) {
        if (subscribedClasses.containsKey(o.getClass())) subscribedClasses.get(o.getClass()).unsubscribe(o);
    }

    public void send(IEvent event) {
        for (SubscriptionClass subscriptionClass : subscribedClasses.values()) {
            subscriptionClass.invoke(event);
        }
    }
}