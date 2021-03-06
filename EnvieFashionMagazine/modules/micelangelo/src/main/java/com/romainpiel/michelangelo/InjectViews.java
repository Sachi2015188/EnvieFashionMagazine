package com.romainpiel.michelangelo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Michelangelo
 * romainpiel
 * 23/01/2014
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface InjectViews {
    Injector value() default Injector.BUTTERKNIFE;
}
