package org.kangspace.cloud.boot.sample.factorybean;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * 加载配置类
 * @author kango2gler@gmail.com
 * @since 2022/6/18
 */
@ComponentScan(
        basePackageClasses = LoadConfigMain.class
)
public class LoadConfigMain {
}
