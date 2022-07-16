package org.kangspace.cloud.boot.sample._import;

import org.springframework.context.annotation.Import;

/**
 * 使用Import导入自定义{@link org.springframework.context.annotation.ImportBeanDefinitionRegistrar}
 * @author kango2gler@gmail.com
 * @since 2022/6/14
 */
@Import(CustomImportBeanDefinitionRegistrar.class)
public class ImportCustomImportBeanDefinitionRegistrar {
}
