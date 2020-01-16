package org.kangspace.common.annotation.test;

import org.kangspace.common.annotation.MyAnnotationTypeEnum;
import org.kangspace.common.annotation.MyClassAnnotation;
import org.kangspace.common.annotation.MyFieldMethodAnnotation;

/**
 * @author kangxuefeng@etiantian.com
 * @desc 我的类1
 * @date 2017/6/23 17:17
 */
@MyClassAnnotation(value = "MyClass1",type = MyAnnotationTypeEnum.CLASS,clazz = MyClass1.class)
@MyClassAnnotation(value = "MyClass1_1",type = MyAnnotationTypeEnum.CLASS,clazz = MyClass1.class)
public class MyClass1 {
    @MyFieldMethodAnnotation(value = "myField1",type = MyAnnotationTypeEnum.FIELD)
    public String myField1;

    @MyFieldMethodAnnotation(value = "myMethod1", type = MyAnnotationTypeEnum.METHOD)
    public String myMethod1(@MyFieldMethodAnnotation(value ="myParam1",type = MyAnnotationTypeEnum.PARAM) String myParam1) {
        return this.getClass().getName();
    }

    @MyFieldMethodAnnotation(value = "myMethod2", type = MyAnnotationTypeEnum.METHOD)
    public String myMethod2(@MyFieldMethodAnnotation(value ="myParam1",type = MyAnnotationTypeEnum.PARAM) String myParam1 ,
                            int myParam2,@MyFieldMethodAnnotation(value ="myParam3",type = MyAnnotationTypeEnum.PARAM) Object myParam3) {
        return this.getClass().getName();
    }
}
