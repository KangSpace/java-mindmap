package org.kangspace.common.reflex;

/**
 * 2019/11/29 12:00
 * 反射测试对象
 * @author kango2gler@gmail.com
 */
@ReflexAnnotation(name = "ReflexClass",value = ReflexClass.class)
public class ReflexClass extends ReflexClassParent implements IReflexClassInterface{
    @ReflexAnnotation(name = "ReflexClass.field",value = String.class)
    String field = "ReflexClass.field";

    @ReflexAnnotation(name = "ReflexClass()",value = ReflexClass.class)
    public ReflexClass(){
       class LocalReflexClassI{}
        System.out.println(new LocalReflexClassI().getClass().getEnclosingConstructor());
    }
    @ReflexAnnotation(name = "ReflexClass(String field)",value = ReflexClass.class)
    public ReflexClass(@ReflexAnnotation(name = "field",value = String.class)String field) {
        this.field = field;
    }


    @ReflexAnnotation(name = "m1()",value = Void.class)
    void m1(@ReflexAnnotation(name = "m1",value = String.class) String[] m1) {
        @ReflexAnnotation(name = "localVarible",value = String.class)
        String localVarible = "localVarible";
        class LocalReflexClassM1{}
    }

    public class MemberClassReflexClass{
    }
    public interface MemberInterfaceReflexClass{
    }
    public enum MemberEnumReflexClass{

    }
}
