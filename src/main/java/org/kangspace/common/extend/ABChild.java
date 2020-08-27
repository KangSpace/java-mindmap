package org.kangspace.common.extend;

/**
 * <pre>
 * 接口 {@link IInterfaceA} {@link IInterfaceB}的实现类
 * </pre>
 *
 * @author kangxuefeng@etiantian.com
 * @date 2020/8/26 10:40
 */
public class ABChild extends ChildParent implements IInterfaceA,IInterfaceB {
    @Override
    public void common() throws Exception{
        System.out.println("IInterfaceA,IInterfaceB 的实现类方法");
    }

    @Override
    public void rootCommon() {
        System.out.println("IInterfaceA,IInterfaceB 父类的实现类方法");
    }
}
