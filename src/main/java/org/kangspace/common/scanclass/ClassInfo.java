package org.kangspace.common.scanclass;

/**
 * @author kangxuefeng@etiantian.com
 * @desc 扫描到的类/文件实体
 * @date 2017/6/28 9:55
 */
public class ClassInfo {
    /**
     * 1. 类名,全名,含包路径
     * 2. 文件名,不含路径
     */
    private String name;
    /**
     * 文件url
     */
    private String url;
    /**
     * 文件类型
     * file,jar
     */
    private String protocol;
    /**
     * jar名称
     */
    private String jarName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getJarName() {
        return jarName;
    }

    public void setJarName(String jarName) {
        this.jarName = jarName;
    }

    public ClassInfo() {
    }

    public ClassInfo(String name, String url, String protocol) {
        this.name = name;
        this.url = url;
        this.protocol = protocol;
    }

    public ClassInfo(String name, String url, String protocol, String jarName) {
        this.name = name;
        this.url = url;
        this.protocol = protocol;
        this.jarName = jarName;
    }
}
