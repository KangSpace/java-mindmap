package org.kangspace.common.scanclass;

/**
 * @author kangxuefeng@etiantian.com
 * @desc 协议
 * @date 2017/6/28 13:41
 */
public enum Protocol {
    /**
     * file 类型协议
     */
    JAR("jar"),
    /**
     * file 类型协议
     */
    FILE("file");

    Protocol(String desc) {
        this.desc = desc;
    }
    private String desc;
    @Override
    public String toString() {
        return this.desc;
    }
}
