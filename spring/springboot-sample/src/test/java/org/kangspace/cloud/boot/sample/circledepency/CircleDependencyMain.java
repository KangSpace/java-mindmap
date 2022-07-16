package org.kangspace.cloud.boot.sample.circledepency;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 属性循环依赖测试
 * user 依赖Group, Group依赖GroupItem, GroupItem依赖User
 *
 * @author kango2gler@gmail.com
 * @since 2022/6/20
 */
@Configuration
@Slf4j
public class CircleDependencyMain implements BeanPostProcessor, BeanFactoryAware {
    private BeanFactory beanFactory;
    private static AtomicBoolean LOADED = new AtomicBoolean(false);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (!LOADED.getAndSet(true)) {
            User user = beanFactory.getBean(User.class);
            log.info("load user:{}", user);
        }
        return bean;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    /**
     * 用户，用户有组的属性,
     * 用户和组产生循环依赖
     */
    @Component
    public static class User implements InitializingBean {
        @Autowired
        private Group group;

        @Override
        public void afterPropertiesSet() throws Exception {
            log.info("user 加载完成：{}", this);
        }
    }

    /**
     * User bean初始化完成后返回一个新的对象
     */
    @Component
    public static class UserBeanPostProcessor implements BeanPostProcessor {
        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            if (bean instanceof User) {
                log.info("UserBeanPostProcessor create new user;");
                return new User();
            }
            return bean;
        }
    }


    /**
     * 组,组内组元素列表
     */
    @Component
    public static class Group implements InitializingBean {
        @Autowired
        private List<GroupItem> items;
        @Autowired
        private User user;

        @Override
        public void afterPropertiesSet() throws Exception {
            log.info("group 加载完成：{}", this);
        }
    }

    /**
     * 组元素内容，包含User列表
     */
    @Component
    public static class GroupItem implements InitializingBean {
        @Autowired
        private List<User> users;

        @Override
        public void afterPropertiesSet() throws Exception {
            log.info("GroupItem 加载完成：{}", this);
        }
    }
}
