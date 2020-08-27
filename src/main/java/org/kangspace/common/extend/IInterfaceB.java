package org.kangspace.common.extend;

import java.io.IOException;

/**
 * <pre>
 * 接口B,定义了common()方法
 * </pre>
 *
 * @author kangxuefeng@etiantian.com
 * @date 2020/8/26 10:38
 */
public interface IInterfaceB extends IInterfaceParent{
    /**
     * 公共方法
     */
    void common() throws IOException,Exception;
}
