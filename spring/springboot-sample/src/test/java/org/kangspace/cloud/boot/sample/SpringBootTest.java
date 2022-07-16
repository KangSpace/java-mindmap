package org.kangspace.cloud.boot.sample;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.kangspace.cloud.boot.sample._autowired.AutowiredMethodParamLoadConfigMain;
import org.kangspace.cloud.boot.sample._autowired.ManualAutowiredBean;
import org.kangspace.cloud.boot.sample._autowired.generic.GenericAutowiredBean;
import org.kangspace.cloud.boot.sample._import.ImportCustomImportBeanDefinitionRegistrar;
import org.kangspace.cloud.boot.sample._import.ImportCustomImportSelector;
import org.kangspace.cloud.boot.sample._resource.ResourceMethodParamLoadConfigMain;
import org.kangspace.cloud.boot.sample.aop.AopMain;
import org.kangspace.cloud.boot.sample.bean.ConfigurationBean;
import org.kangspace.cloud.boot.sample.bean.ConfigurationConditionBean;
import org.kangspace.cloud.boot.sample.circledepency.CircleDependencyMain;
import org.kangspace.cloud.boot.sample.condition.ImportCustomConditionBean;
import org.kangspace.cloud.boot.sample.factorybean.LoadConfigMain;
import org.kangspace.cloud.boot.sample.factorybean.UserBean;
import org.kangspace.cloud.boot.sample.instantiationstrategy.InstantiationStrategyMain;
import org.kangspace.cloud.boot.sample.listener.ListenerMain;
import org.springframework.beans.factory.BeanCurrentlyInCreationException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;

/**
 * Spring测试类
 * <pre>
 * 1. &#64;Import的value常见的有5种用法
 * value为普通的类
 * value为@Configuration标注的类
 * value为@CompontentScan标注的类
 * value为ImportBeanDefinitionRegistrar接口类型
 * value为ImportSelector接口类型
 * value为DeferredImportSelector接口类型
 * </pre>
 *
 * @author kango2gler@gmail.com
 * @since 2022/6/13
 */
@Slf4j
@RunWith(JUnit4.class)
public class SpringBootTest {


    /**
     * &#064;Configuration &#064;Bean 加载测试
     */
    @Test
    public void atConfigurationBeanTest() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigurationBean.AtConfigurationBean.class);
        ConfigurationBean.AtConfigurationBean bean = context.getBean(ConfigurationBean.AtConfigurationBean.class);
        log.info("bean: {}", bean);
    }

    /**
     * 普通类 &#064;Bean 加载测试
     */
    @Test
    public void atNormalClassBeanTest() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigurationBean.NormalBean.class);
        ConfigurationBean.NormalBean bean = context.getBean(ConfigurationBean.NormalBean.class);
        log.info("bean: {}", bean);
    }


    /**
     * {@link ImportBeanDefinitionRegistrar} &#064;Bean 加载测试
     */
    @Test
    public void importBeanDefinitionRegistrarTest() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ImportCustomImportBeanDefinitionRegistrar.class);
        ConfigurationBean.Bean1 bean = context.getBean(ConfigurationBean.Bean1.class);
        ConfigurationBean.Bean2 bean2 = context.getBean(ConfigurationBean.Bean2.class);
        log.info("bean1: {}, bean2: {}", bean, bean2);
    }


    /**
     * {@link org.springframework.context.annotation.ImportSelector} &#064;Bean 加载测试
     */
    @Test
    public void importSelectorTest() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ImportCustomImportSelector.class);
        ConfigurationBean.Bean1 bean = context.getBean(ConfigurationBean.Bean1.class);
        ConfigurationBean.Bean2 bean2 = context.getBean(ConfigurationBean.Bean2.class);
        log.info("bean1: {}, bean2: {}", bean, bean2);
    }

    /**
     * {@link org.springframework.context.annotation.Conditional} &#064;Bean 加载测试
     */
    @Test
    public void customConditionBeanTest() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ImportCustomConditionBean.class);
        ConfigurationConditionBean.Bean1 bean = context.getBean(ConfigurationConditionBean.Bean1.class);
        ConfigurationConditionBean.Bean2 bean2 = null;
        try {
            bean2 = context.getBean(ConfigurationConditionBean.Bean2.class);
        } catch (Exception e) {

        }
        log.info("bean1: {}, bean2: {}", bean, bean2);
    }


    /**
     * {@link org.springframework.beans.factory.FactoryBean} &#064;Bean 加载测试
     */
    @Test
    public void customFactoryBeanTest() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LoadConfigMain.class);
        UserBean bean = context.getBean(UserBean.class);
        log.info("bean: {} ", bean);
    }

    /**
     * Autowired 方法多参数注入Bean 加载测试
     */
    @Test
    public void autowiredMethodParamTest() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AutowiredMethodParamLoadConfigMain.class);
        AutowiredMethodParamLoadConfigMain bean = context.getBean(AutowiredMethodParamLoadConfigMain.class);
        log.info("bean: {} ", bean);
    }

    /**
     * Resource 方法多参数注入Bean 加载测试
     */
    @Test
    public void resourceMethodParamTest() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ResourceMethodParamLoadConfigMain.class);
        ResourceMethodParamLoadConfigMain bean = context.getBean(ResourceMethodParamLoadConfigMain.class);
        log.info("bean: {} ", bean);
    }


    /**
     * 指定BeanDefinition.autowired_mode时属性注入测试,
     * 测试属性不加@Autowired是否可以被注入
     */
    @Test
    public void specificAutowiredNormalBeanPropertyValueTest() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.registerBeanDefinition("userBean",
                BeanDefinitionBuilder.rootBeanDefinition(org.kangspace.cloud.boot.sample._autowired.UserBean.class)
                        .getBeanDefinition());
        beanFactory.registerBeanDefinition("manualAutowiredBean",
                BeanDefinitionBuilder.rootBeanDefinition(ManualAutowiredBean.class)
                        .setAutowireMode(AutowireCapableBeanFactory.AUTOWIRE_BY_NAME)
                        .getBeanDefinition());
        org.kangspace.cloud.boot.sample._autowired.UserBean userBean = (org.kangspace.cloud.boot.sample._autowired.UserBean) beanFactory.getBean("userBean");
        ManualAutowiredBean manualAutowiredBean = (ManualAutowiredBean) beanFactory.getBean("manualAutowiredBean");
        log.info("userBean:{} \n manualAutowiredBean: {} ", userBean, manualAutowiredBean);
    }

    /**
     * 泛型注入Bean测试
     *
     * @see GenericAutowiredBean
     */
    @Test
    public void genericAutowiredTest() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(GenericAutowiredBean.class);
        GenericAutowiredBean bean = context.getBean(GenericAutowiredBean.class);
        log.info("bean: {} ", bean);
    }

    /**
     * Listener 测试
     *
     * @see ListenerMain linstener
     */
    @Test
    public void diyListenerTest() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ListenerMain.class);
        ListenerMain bean = context.getBean(ListenerMain.class);
        log.info("bean: {} ", bean);
    }


    /**
     * 属性循环依赖 测试
     * a->b,b->a
     * 抛出{@link BeanCurrentlyInCreationException}
     *
     * @see ListenerMain linstener
     */
    @Test
    public void circleDependencyTest() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(CircleDependencyMain.class);
        context.refresh();
    }

    @AllArgsConstructor
    @Setter
    class UserFieldClass{
        private String name;

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    "},"+super.toString();
        }
    }

    /**
     * 对象赋值测试
     */
    @Test
    public void objectSetTests() {
        @AllArgsConstructor
        @Setter
        class UserMethodClass{
            private String name;

            @Override
            public String toString() {
                return "User{" +
                        "name='" + name + '\'' +
                        "},"+super.toString();
            }
        }

        UserMethodClass src = new UserMethodClass("src");
        log.info("src:{}", src);
        UserMethodClass dest = src;
        log.info("dest:{}", dest);
        dest.setName("dest");
        log.info("update dest:{}, src:{}", dest, src);
        dest = new UserMethodClass("dest");
        log.info("new dest:{}", dest);
        log.info("new dest == src :{}",dest == src);
    }



    /**
     * 实例化加载测试
     *
     * @see InstantiationStrategyMain main
     */
    @Test
    public void instantiationStrategyTest() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(InstantiationStrategyMain.class);
        InstantiationStrategyMain.NormalNoOverrideMethodNoParentBean bean1 = context.getBean(InstantiationStrategyMain.NormalNoOverrideMethodNoParentBean.class);
        InstantiationStrategyMain.NormalNoOverrideMethodHasParentBean bean2 = context.getBean(InstantiationStrategyMain.NormalNoOverrideMethodHasParentBean.class);
        InstantiationStrategyMain.NormalHasOverrideMethodHasParentBean bean3 = context.getBean(InstantiationStrategyMain.NormalHasOverrideMethodHasParentBean.class);
        log.info("\n 无父类的Bean:{} \n 有父类，无@Override的类:{} \n 有父类，有@Override的类:{}\n ", bean1, bean2, bean3);
    }

    /**
     * 手工aop测试
     */
    @Test
    public void manualAopTest() {
        AopMain aopMain = new AopMain();
        aopMain.manualAop();
    }
    /**
     * Spring中手工aop测试
     */
    @Test
    public void manualAopInSpringTest() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopMain.class);
        AopMain aopMain = context.getBean(AopMain.class);
        aopMain.manualAop();
    }
}
