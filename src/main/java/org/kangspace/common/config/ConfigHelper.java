package org.kangspace.common.config;

import java.util.ResourceBundle;

/**
 * @author kangxuefeng@etiantian.com
 * @desc mongo.properties配置文件帮助类
 * @date 2017/2/10 17:32
 */
public class ConfigHelper {

    /**
     * 配置文件名称
     * x.properties
     */
    private String propertiesFileName;

    private ResourceBundle bundle = null;

    public ConfigHelper(String propertiesFileName){
        this.propertiesFileName = propertiesFileName;
        init();
    }


    /**
     * 初始化
     * @Author kangxuefeng@etiantian.com
     * @Date 2017/2/13 15:27
     * @return
     */
    private void init() {
        try{
            bundle = ResourceBundle.getBundle(propertiesFileName);
        }catch (Exception e){
            throw new RuntimeException(propertiesFileName+"  not found ,must provide "+propertiesFileName+" under 'classes' to call this package! -_-!!!",e);
        }
    }
    /**
     * 通过key获取微信配置文件信息
     * @param key
     * @Author kangxuefeng@etiantian.com
     * @Date 2017/2/10 17:34
     * @return value
     */
    public String getValue(String key){
        String val = null;
        try {
            if(!bundle.containsKey(key)) {
                throw new NoSuchFieldException(key);
            }
            val = (String) bundle.getObject(key);
        }catch (Exception e){
            throw new RuntimeException("key:"+key+" not found ,must provide key:"+key+" in "+propertiesFileName+"! -_-!!!",e);
        }
        return val;
    }
}
