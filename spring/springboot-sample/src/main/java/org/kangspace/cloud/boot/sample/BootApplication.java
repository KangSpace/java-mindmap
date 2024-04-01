package org.kangspace.cloud.boot.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.TimeZone;

/**
 * 启动程序
 * @author kango2gler@gmail.com
 */
//@EnableDiscoveryClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan("org.kangspace")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class BootApplication {
    public static void main(String[] args) {
        setTimezone();
        setDevtoolsRestart();
        SpringApplication.run(BootApplication.class, args);
        System.out.println("Server启动成功. \n");
    }

    private static void setTimezone() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
    }

    private static void setDevtoolsRestart() {
        System.setProperty("spring.devtools.restart.enabled", "false");
    }


}
