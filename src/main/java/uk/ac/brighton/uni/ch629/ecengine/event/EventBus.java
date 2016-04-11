package uk.ac.brighton.uni.ch629.ecengine.event;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EventBus
{
    private HashMap<String, List<InstancedMethod>> subscribedMethods; //Using SimpleName

    public EventBus()
    {
        subscribedMethods = new HashMap<String, List<InstancedMethod>>();
    }

    public void send(IEvent event)
    {
        List<InstancedMethod> methods = subscribedMethods.get(event.getClass().getSimpleName());

        for (InstancedMethod mtd : methods)
        {
            mtd.invoke(event);
        }
    }

    public void registerListener(IListener listener)
    { //FIXME: IDK if I need IListener, could just be any Class
        for (Method mtd : listener.getClass().getDeclaredMethods())
        {
            if (mtd.isAnnotationPresent(SubscribeEvent.class))
            {
                if (mtd.getParameterTypes().length == 1 && mtd.getParameterTypes()[0].getClass().isInstance(IEvent.class))
                {
                    Class<? extends IEvent> param = (Class<? extends IEvent>) mtd.getParameterTypes()[0];
                    addMethod(param, mtd, listener);
                }
            }
        }
    }

    public void registerEvent(Class<? extends IEvent> event)
    { //TODO: throws new DuplicateEventException?
        if (!subscribedMethods.containsKey(event.getSimpleName()))
            subscribedMethods.put(event.getSimpleName(), new ArrayList<InstancedMethod>());
    }

    private void addMethod(Class<? extends IEvent> event, Method method, Object object)
    {
        addMethod(event, new InstancedMethod(method, object));
    }

    private void addMethod(Class<? extends IEvent> event, InstancedMethod method)
    {
        if (subscribedMethods.containsKey(event.getSimpleName()))
        {
            List<InstancedMethod> methods = subscribedMethods.get(event.getSimpleName());
            methods.add(method);
            subscribedMethods.put(event.getSimpleName(), methods); //TODO: Could do checks if the method already exists.
        }
    }
}