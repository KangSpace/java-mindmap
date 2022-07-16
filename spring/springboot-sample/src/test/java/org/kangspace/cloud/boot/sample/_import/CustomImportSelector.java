package org.kangspace.cloud.boot.sample._import;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 自定义{@link ImportSelector}实现类，使用 &#64;import 加载
 * @author kango2gler@gmail.com
 * @since 2022/6/14
 */
public class CustomImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{
                CustomImportBeanDefinitionRegistrar.class.getName()
        };
    }
}
