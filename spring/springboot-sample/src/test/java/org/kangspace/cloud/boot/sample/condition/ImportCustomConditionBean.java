package org.kangspace.cloud.boot.sample.condition;

import org.kangspace.cloud.boot.sample.bean.ConfigurationConditionBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 加载带有条件的bean
 * @author kango2gler@gmail.com
 * @since 2022/6/14
 */
@Configuration
@Import({ConfigurationConditionBean.AtConfigurationBean.class})
public class ImportCustomConditionBean {
}
