package org.kangspace.cloud.boot.sample._resource;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * 加载配置类
 *
 * @author kango2gler@gmail.com
 * @since 2022/6/18
 */
@Slf4j
@ComponentScan(
        basePackageClasses = ResourceMethodParamLoadConfigMain.class
)
@Data
public class ResourceMethodParamLoadConfigMain {
    private UserBean userBean;
    private UserBean userBean2;
    private UserBean userBeanSpecial;

    /**
     * Resource 只支持单个参数注入
     * @param userBean bean1
     * @param userBean2 bean2
     * @see org.springframework.context.annotation.CommonAnnotationBeanPostProcessor.ResourceElement#buildResourceMetadata(Class)
     */
//    @Resource
    public void injectMethodByParam(UserBean userBean, UserBean userBean2) {
        this.userBean = userBean;
        this.userBean2 = userBean2;
        log.info("injectMethodByParam userBean: {}, info: {}", userBean, userBean2);
    }

    @Resource
    public void setUserBeanSpecial(UserBean userBean) {
        this.userBeanSpecial = userBean;
        log.info("setUserBeanSpecial userBean: {}, info: {}", userBean, userBean2);
    }

    @Configuration
    public static class InitBean {
        @Bean
        public UserBean.Info userBeanInfo() {
            return new UserBean.Info("inject");
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
