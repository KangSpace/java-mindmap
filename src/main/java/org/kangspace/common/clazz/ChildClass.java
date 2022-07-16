package org.kangspace.common.clazz;

/**
 * 子类
 *
 * 匿名类/局部类调用所在类的成员变量和局部变量时,需要将传入的变量转换为final,防止成员变量和局部变量在匿名类中被修改，变量在所在类运行结束后，可能依然在匿名类/局部类中被使用，导致内存泄漏。

 匿名类/局部类调用所在类的成员变量和局部变量时,会将变量生成为自己的成员变量，并生成该成员变量的构造方法，在调用位置使用改构造初始化对象。，并被final修饰。

 使用所在类成员变量时，将所在类加入到成员变量中并创建创建基于所在类的构造方法，即 new Clasz(this,变量) ，变量名为this$0;
 使用所在类局部变量时，会将局部变量加入到成员变量中，并创建创建基于所在类的构造方法，，即 new Clasz(final 变量)：局部变量的变量名为val$变量名，并被final修饰。

 *
 * 2019/11/22 13:19
 *
 * @author kango2gler@gmail.com
 */
public class ChildClass extends ParentClass{
    final static String cs = "ChildClass final String cs";
    static int ci = 0;
    int fint = 0;
    ChildClass(){
        class LocalClass{
            public void run(){
                fint = 1;
                System.out.println(fint);
            }
        }
        class LocalClass3{
            public void run(){
                fint = 1;
                System.out.println(fint);
            }
        }
//        System.out.println(new LocalClass());
//        System.out.println(new LocalClass3());
        System.out.println("ChildClass Non-ArgsConstruct run"+" Thread:"+Thread.currentThread().getName());
    }
    ChildClass(int i ){
        class LocalClass{
            public void run(){
                fint = 1;
                System.out.println(fint+i);
            }
        }
        class LocalClass2{
            public void run(){
                fint = 1;
                System.out.println(fint+i);
            }
        }
        LocalClass lc = new LocalClass();
        LocalClass lc2 = new LocalClass();
//        System.out.println(lc);
//        System.out.println(lc2);
//        Class c = new ChildClass(){
//          public void run(){
//              // i = 1;
//              System.out.println(i);
//              System.out.println(fint);
//          }
//        }.getClass();
//        System.out.println(c);

        System.out.println("ChildClass ArgsConstruct ChildClass(int i ): run"+" i: "+i +" Thread:"+Thread.currentThread().getName());
    }

    static {
        System.out.println("ChildClass static block run: cs:"+cs+" , ci:"+ci+" , ps:"+ps+" , pi:"+pi+" Thread:"+Thread.currentThread().getName());
    }

    class InnerClass{
        public void run(){
            System.out.println(fint);
        }
    }

    {
        System.out.println("this is a normal code block in class, it will be add to non-args Construct method;");
    }

    public static void main(String[] args) {
//        Class c=  new ChildClass(1).new InnerClass().getClass();
//        System.out.println(c.getDeclaredFields());
        ChildClass c=  new ChildClass(1);
        c.syncThisFn();
    }
}
