package uk.ac.brighton.uni.ch629.ecengine.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class SubscriptionClass<T> {
    private final Set<T> objects; //Holds the objects to invoke the methods from.
    private final HashMap<String, Set<Method>> methods; //Holds each method in one class to be invoked.

    public SubscriptionClass() {
        objects = new HashSet<T>();
        methods = new HashMap<String, Set<Method>>();
    }

    public void invoke(IEvent event) {
        if (methods.containsKey(event.getClass().getSimpleName())) {
            Set<Method> mtds = methods.get(event.getClass().getSimpleName());
            for (Method method : mtds) {
                for (T object : objects) {
                    try {
                        method.invoke(object, event);
                    } catch (IllegalAccessException ex) {
                        ex.printStackTrace();
                    } catch (InvocationTargetException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

    public synchronized void subscribe(T object) {
        objects.add(object);
    }

    public synchronized void unsubscribe(T object) {
        if (objects.contains(object)) objects.remove(object);
    }

    public synchronized void addMethod(Class<? extends IEvent> event, Method mtd) {
        Set<Method> mtds;
        if (methods.containsKey(event.getSimpleName())) mtds = methods.get(event.getSimpleName());
        else mtds = new HashSet<>();
        mtds.add(mtd);
        methods.put(event.getSimpleName(), mtds);
    }
}