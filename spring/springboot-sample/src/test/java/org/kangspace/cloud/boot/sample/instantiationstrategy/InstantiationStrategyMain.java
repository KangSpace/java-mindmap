package org.kangspace.cloud.boot.sample.instantiationstrategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * 实例化方式(反射/CGLib子类)
 * 1. 反射用于创建无@Override方法的类，即无重写父类方法的类
 * 2. CGLib用于创建有@Override的类，即有重写父类方法的类
 * @author kango2gler@gmail.com
 * @since 2022/6/21
 */
@Slf4j
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Configuration
public class InstantiationStrategyMain {

    /**
     * 1. 无父类的Bean
     */
    @Component
    public static class NormalNoOverrideMethodNoParentBean{
        public void m() {
            log.info("NormalNoOverrideMethodNoParentBean.m()");
        }
    }

    public static class NoMethodParent{}
    /**
     * 2. 有父类，无@Override的类
     */
    @Component
    public static class NormalNoOverrideMethodHasParentBean extends NoMethodParent{
        public void m() {
            log.info("NormalNoOverrideMethodHasParentBean.m()");
        }
    }

    /**
     * 有方法的抽象父类
     */
    public static abstract class HasMethodParent{
        public abstract void m1();
        public static void m2(){}
    }
    /**
     * 3. 有父类，有@Override的类
     */
    @Component
    public static class NormalHasOverrideMethodHasParentBean extends HasMethodParent{
        public void m() {
            log.info("NormalHasOverrideMethodHasParentBean.m()");
        }
        @Override
        public void m1() {
            log.info("NormalHasOverrideMethodHasParentBean.m1() override");
            this.m();
        }
    }
}
