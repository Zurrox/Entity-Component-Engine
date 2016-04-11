package uk.ac.brighton.uni.ch629.ecengine.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InstancedMethod
{
    private Method method;
    private Object object;

    public InstancedMethod(final Method method, final Object object)
    {
        this.method = method;
        this.object = object;
    }

    public Object invoke(Object... args)
    {
        try
        {
            return method.invoke(object, args);
        } catch (IllegalAccessException e)
        {
            e.printStackTrace();
        } catch (InvocationTargetException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public Method getMethod()
    {
        return method;
    }

    public Object getObject()
    {
        return object;
    }
}