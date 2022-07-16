package org.kangspace.cloud.boot.sample.condition;

import lombok.extern.slf4j.Slf4j;
import org.apache.naming.factory.BeanFactory;
import org.kangspace.cloud.boot.sample.bean.ConfigurationConditionBean;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.ConfigurationCondition;
import org.springframework.core.Ordered;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.ClassUtils;

import java.util.Map;

/**
 * 自定义ConfigurationCondition 实现类,指定解析阶段条件处理
 * @author kango2gler@gmail.com
 * @since 2022/6/14
 */
@Slf4j
public class CustomParseConfigurationCondition implements ConfigurationCondition, Ordered {
    @Override
    public ConfigurationPhase getConfigurationPhase() {
        return ConfigurationPhase.PARSE_CONFIGURATION;
    }

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        log.info("CustomParseConfigurationCondition match, metadata:{}", metadata);
        // 检查类存不存在
        try {
            ClassUtils.forName(ConfigurationConditionBean.Bean1.class.getName(), ClassUtils.getDefaultClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
