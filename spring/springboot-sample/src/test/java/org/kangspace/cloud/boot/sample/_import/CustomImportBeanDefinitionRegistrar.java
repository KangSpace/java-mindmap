package org.kangspace.cloud.boot.sample._import;

import lombok.extern.slf4j.Slf4j;
import org.kangspace.cloud.boot.sample.bean.ConfigurationBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 自定义BeanDefinitionRegistrar, 可以通过API方式注册Spring Bean,
 * 使用{@link org.springframework.context.annotation.AnnotationConfigApplicationContext} 加载该类
 * @see ImportBeanDefinitionRegistrar
 * @author kango2gler@gmail.com
 * @since 2022/6/14
 */
@Slf4j
public class CustomImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {
        BeanDefinition bean1 = BeanDefinitionBuilder.genericBeanDefinition(ConfigurationBean.Bean1.class).getBeanDefinition();
        BeanDefinition bean2 = BeanDefinitionBuilder.genericBeanDefinition(ConfigurationBean.Bean2.class)
                .addConstructorArgReference("bean1").getBeanDefinition();
        // 注册一个Bean
        registry.registerBeanDefinition("bean1",bean1);
        registry.registerBeanDefinition("bean2",bean2);
        log.info("CustomImportBeanDefinitionRegistrar: 注册bean1:{} , bean2:{}", bean1, bean2);
    }
}
