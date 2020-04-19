package org.kangspace.common.annotation.impl;

import org.kangspace.common.annotation.MyAnnotationTypeEnum;
import org.kangspace.common.annotation.MyClassAnnotation;

import java.lang.annotation.Annotation;

/**
 * 2019/11/28 16:43
 *
 * @author kango2gler@gmail.com
 */
public class MyClassAnnotationImpl implements MyClassAnnotation {
    @Override
    public String[] value() {
        return new String[0];
    }

    @Override
    public MyAnnotationTypeEnum type() {
        return null;
    }

    @Override
    public Class clazz() {
        return null;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}
