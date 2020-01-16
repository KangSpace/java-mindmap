package org.kangspace.common.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解,成员变量方法注解
 * @Author kangxuefeng@etiantian.com
 * @Date 2017/6/23 16:53
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.FIELD,ElementType.METHOD,ElementType.PARAMETER})
@Documented
public @interface MyFieldMethodAnnotation {
    String value();
    MyAnnotationTypeEnum type();

}
