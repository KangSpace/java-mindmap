package org.kangspace.cloud.boot.sample._autowired;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * 加载配置类
 *
 * @author kango2gler@gmail.com
 * @since 2022/6/18
 */
@Slf4j
@ComponentScan(
        basePackageClasses = AutowiredMethodParamLoadConfigMain.class
)
@Data
public class AutowiredMethodParamLoadConfigMain {
    private UserBean userBean;
    private UserBean userBean2;

    /**
     * 构造方法不加@Autowired也会自动注入
     * @param userBean bean1
     * @param userBean2 bean2
     */
    public AutowiredMethodParamLoadConfigMain(UserBean userBean, UserBean userBean2) {
//        this.userBean = userBean;
//        this.userBean2 = userBean2;
    }

    @Autowired
    public void injectMethodByParam(UserBean userBean, UserBean userBean2) {
        this.userBean = userBean;
        this.userBean2 = userBean2;
        log.info("userBean: {}, info: {}", userBean, userBean2);
    }

    @Configuration
    public static class InitBean {
        @Bean
        public UserBean.Info userBeanInfo() {
            return new UserBean.Info();
        }

        @Bean
        public UserBean userBean2() {
            return new UserBean("autowired userBean2", 1, userBeanInfo());
        }

        @Bean
        public UserBean userBean() {
            return new UserBean("autowired userBean1", 1, userBeanInfo());
        }
    }
}
