package org.kangspace.cloud.boot.sample.condition;

import lombok.extern.slf4j.Slf4j;
import org.kangspace.cloud.boot.sample.bean.ConfigurationConditionBean;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.ConfigurationCondition;
import org.springframework.core.annotation.Order;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

/**
 * 自定义ConfigurationCondition 实现类,指定注册Bean阶段条件处理
 * @author kango2gler@gmail.com
 * @since 2022/6/14
 */
@Slf4j
@Order(1)
public class CustomRegisterConfigurationCondition implements ConfigurationCondition {
    @Override
    public ConfigurationPhase getConfigurationPhase() {
        return ConfigurationPhase.REGISTER_BEAN;
    }

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        log.info("CustomRegisterConfigurationCondition match, metadata:{}", metadata);
        // Bean存不存在
        ConfigurableListableBeanFactory factory = context.getBeanFactory();
        Map<String,?> beans = factory.getBeansOfType(ConfigurationConditionBean.Bean1.class);
        // TODO 切换条件
        return beans.isEmpty();
    }
}
