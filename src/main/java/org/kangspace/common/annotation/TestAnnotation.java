package org.kangspace.common.annotation;

import org.kangspace.common.annotation.test.MyClass1;
import org.kangspace.common.util.ConsoleLogger;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Hashtable;

/**
 * @author kangxuefeng@etiantian.com
 * @desc 自定义Annotation测试
 * @date 2017/6/23 17:24
 */
public class TestAnnotation {
    private ConsoleLogger logger = new ConsoleLogger();
    private final static String TAB_STR = "\t";
    public void test1() throws IllegalAccessException, InvocationTargetException {
        logger.info("===开始===");
        MyClass1 myClass1 = new MyClass1();
        //获取类注解
        getAnnotations(myClass1,TAB_STR);
        //获取成员变量和方法
        Class myClass1Clazz = myClass1.getClass();
        //成员变量
        Field[] fields  = myClass1Clazz.getDeclaredFields();
        for (Field field : fields) {
            Object obj = field.get(myClass1);
            logger.info(TAB_STR+"field: name =" +field.getName() +" , type = "+ field.getType() +" , value = "+ (obj instanceof Object[]?Arrays.toString(((Object[])obj)):obj));
            getAnnotations(field,(TAB_STR+TAB_STR));
        }

        //方法
        Method[] methods = myClass1Clazz.getDeclaredMethods();
        for (Method method : methods) {
            StringBuffer paramters = new StringBuffer("(");
            Class[] paramterTypes = method.getParameterTypes();
            method.getParameters();
            for (int i = 0; i < paramterTypes.length; i++) {
                paramters.append(paramterTypes[i].getName());
                if (i != paramterTypes.length - 1)
                    paramters.append(",");
            }
            paramters.append(")");
            logger.info(TAB_STR + "method: name = " + method.getName() + " , return type = " + method.getReturnType() + " ,paramters = " + paramters);
            getAnnotations(method,(TAB_STR+TAB_STR));
            logger.info(TAB_STR + TAB_STR + "parameter annotation:");
            Annotation[][] paramAnnotations  = method.getParameterAnnotations();
            for (Annotation[] i : paramAnnotations) {
                getAnnotations(i,TAB_STR + TAB_STR);
            }
        }

        getFieldsValue(new Object[]{myClass1});
        logger.info("===结束===");
    }


    /**
     * 获取对象的注解及值
     * @param myClass1
     * @Author kangxuefeng@etiantian.com
     * @Date 2017/6/26 9:58
     * @return
     */
    public void getAnnotations(Object myClass1,String tabStr) throws InvocationTargetException, IllegalAccessException {
        Class myClass1Clazz = myClass1.getClass();
        logger.info(tabStr+myClass1.toString()+" - "+myClass1Clazz.toString());
        logger.info(tabStr+"Annotations:");
        Annotation[] annotations = myClass1Clazz.getAnnotations();
        if (myClass1 instanceof Field ) {
            annotations = ((Field) myClass1).getAnnotations();
        } else if (myClass1 instanceof Method) {
            annotations = ((Method) myClass1).getAnnotations();
        }
        logger.info(tabStr+myClass1Clazz.getName()+" : ");
        getAnnotations(annotations,tabStr);
    }

    /**
     * 获取注解中方法的名称和值
     * @param annotations
     * @param tabStr
     * @Author kangxuefeng@etiantian.com
     * @Date 2017/6/26 12:02
     * @return
     */
    public void getAnnotations(Annotation[] annotations,String tabStr) throws InvocationTargetException, IllegalAccessException {
        for (Annotation tempA : annotations) {
            Class srcClass = tempA.annotationType();
            if(srcClass != null){
                Method[] methods =  srcClass.getDeclaredMethods();
                for (Method m : methods) {
                    if (m.getParameterTypes().length == 0) {
                        Object obj = m.invoke(tempA, null);
                        logger.info(tabStr+tabStr+m.getName()+ " = "+ (obj instanceof Object[]?Arrays.toString(((Object[])obj)):obj));
                    }

                }
            }

        }
    }

    /**
     * 获取对象的所有信息
     * @param object
     * @Author kangxuefeng@etiantian.com
     * @Date 2017/6/23 17:42
     * @return
     */
    public Hashtable<String, String> getObjects(Object object) {
        //获取field
        //获取method
        //获取annotation
        return null;
    }

    /**
     * 获取对象的所有成员变量及其值
     * @param objects
     * @Author kangxuefeng@etiantian.com
     * @Date 2017/6/23 17:36
     * @return
     */
    public Hashtable<String,Object> getFieldsValue(Object[] objects) throws IllegalAccessException {
        for (Object tempA : objects) {
            Class aClass = tempA.getClass();
            logger.info(aClass.getName()+" : ");
            Field[] aFields = aClass.getFields();
            for (Field f : aFields) {
                logger.info(f.toGenericString()+" = "+f.get(tempA));
            }
        }
        return null;
    }
    /**
     * 获取对象的所有方法
     * @param objects
     * @Author kangxuefeng@etiantian.com
     * @Date 2017/6/23 17:36
     * @return
     */
    public Hashtable<String,Object> getMethodsValue(Object[] objects){
        return null;
    }

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {
        TestAnnotation testAnnotation = new TestAnnotation();
        testAnnotation.test1();
    }
}
