package org.kangspace.cluod.leaf.sample;

import com.sankuai.inf.leaf.plugin.annotation.EnableLeafServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import java.util.TimeZone;

/**
 * 启动程序
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableLeafServer
@ComponentScan("org.kangspace")
public class LeafApplication {
    public static void main(String[] args) {
        setTimezone();
        setDevtoolsRestart();
        SpringApplication.run(LeafApplication.class, args);
        System.out.println("LMS启动成功. \n");
    }

    private static void setTimezone() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
    }

    private static void setDevtoolsRestart() {
        // TODO It's will be set false on production environment.
        System.setProperty("spring.devtools.restart.enabled", "true");
    }


}
