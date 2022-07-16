package org.kangspace.cloud.boot.sample.bean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.kangspace.cloud.boot.sample.condition.CustomParseConfigurationCondition;
import org.kangspace.cloud.boot.sample.condition.CustomRegisterConfigurationCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration Bean
 * @author kango2gler@gmail.com
 * @since 2022/6/13
 */
@Slf4j
public class ConfigurationConditionBean {

    /**
     * &#064;Configuration 标注的类,
     * 在bean注册阶段校验是否需要处理
     */
    @Configuration
    @Conditional(CustomParseConfigurationCondition.class)
    public static class AtConfigurationBean{
        /**
         * 在解析阶段校验是否需要处理
         */
        @Bean
        public Bean1 getBean1() {
            log.info("getBean1");
            return new Bean1();
        }

        @Bean
        @Conditional(CustomRegisterConfigurationCondition.class)
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
