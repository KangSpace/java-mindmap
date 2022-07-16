package org.kangspace.cloud.boot.sample.factorybean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * UserBean的工厂Bean
 * @author kango2gler@gmail.com
 * @since 2022/6/18
 */
@Component
public class UserBeanFactoryBean implements FactoryBean<UserBean> {
    @Override
    public UserBean getObject() {
        return new UserBean("userBeanFactoryBean",1);
    }

    @Override
    public Class<?> getObjectType() {
        return UserBean.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
