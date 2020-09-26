package org.kangspace.common.extend;

/**
 * <pre>
 * 接口A,定义了common()方法
 * </pre>
 *
 * @author kango2gler@gmail.com
 * @date 2020/8/26 10:38
 */
public interface IInterfaceA extends IInterfaceParent{
    /**
     * 公共方法
     */
    void common() throws RuntimeException, Exception;
}
