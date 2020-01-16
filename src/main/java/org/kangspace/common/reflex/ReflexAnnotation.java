package org.kangspace.common.reflex;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PACKAGE,ElementType.ANNOTATION_TYPE,ElementType.TYPE,ElementType.CONSTRUCTOR, ElementType.FIELD,ElementType.METHOD,ElementType.PARAMETER, ElementType.LOCAL_VARIABLE})
@Inherited
public @interface ReflexAnnotation {
    Class value() default Class.class;
    String name() default "ReflexAnnotation";
}
