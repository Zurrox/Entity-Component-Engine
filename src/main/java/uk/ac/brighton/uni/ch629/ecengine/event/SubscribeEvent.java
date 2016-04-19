package uk.ac.brighton.uni.ch629.ecengine.event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An Annotation to be used on Methods which are listening to Events. These Methods require a single parameter of the
 * Event in which they are listening for.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SubscribeEvent {
}