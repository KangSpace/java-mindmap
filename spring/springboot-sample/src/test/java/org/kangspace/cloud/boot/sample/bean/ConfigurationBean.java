package org.kangspace.cloud.boot.sample.bean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration Bean
 * @author kango2gler@gmail.com
 * @since 2022/6/13
 */
@Slf4j
public class ConfigurationBean {

    /**
     * &#064;Configuration 标注的类
     */
    @Configuration
    public static class AtConfigurationBean{
        @Bean
        public Bean1 getBean1() {
            log.info("getBean1");
            return new Bean1();
        }

        @Bean
        public Bean2 getBean2() {
            Bean1 bean1 = getBean1();
            log.info("getBean2");
            return new Bean2(bean1);
        }

    }


    /**
     * 无&#064;Configuration 标注的普通类
     */
    public static class NormalBean{
        @Bean
        public Bean1 getBean1() {
            log.info("getBean1");
            return new Bean1();
        }

        @Bean
        public Bean2 getBean2() {
            Bean1 bean1 = getBean1();
            log.info("getBean2");
            return new Bean2(bean1);
        }
    }


    public static class Bean1{

    }

    @Data
    public static class Bean2{
        private Bean1 bean1;

        public Bean2(Bean1 bean1) {
            this.bean1 = bean1;
        }
    }

}
