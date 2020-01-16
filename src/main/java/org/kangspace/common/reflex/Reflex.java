package org.kangspace.common.reflex;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
@ReflexAnnotation(value = Reflex.class,name = "Reflex")
public @interface Reflex {
}
