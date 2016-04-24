package uk.ac.brighton.uni.ch629.ecengine.component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ComponentDetails {
    Class<? extends Component>[] dependencies() default {};

    ComponentType type() default ComponentType.MISC;

    enum ComponentType {
        COLLISION,
        RENDER,
        MISC
    }
}