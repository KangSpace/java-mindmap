package org.kangspace.cloud.boot.sample.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.*;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.lang.reflect.Method;

/**
 * @author kango2gler@gmail.com
 * @since 2022/6/22
 */
@Slf4j
@Configuration
@EnableAspectJAutoProxy
@Aspect
public class AopMain {

    public interface IAopService{
        String run();
    }
    public static class AopService implements IAopService{
        {
            log.info("auto generate non-args construct method");
        }
        @Override
        public String run(){
            log.info("AopService origin run");
            return "1";
        };
    }

    /**
     * 手工AOP测试
     */
    public void manualAop() {
//        manualAop(IAopService.class);
        try {
            manualAop(AopService.class);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public <T extends IAopService> void manualAop(Class<T> clazz) throws InstantiationException, IllegalAccessException {
//        AopService target = new AopService();
//        AopService target = new AopService();
        Pointcut pointcut = new Pointcut() {
            @Override
            public ClassFilter getClassFilter() {
                return new ClassFilter() {
                    @Override
                    public boolean matches(Class<?> clazz) {
                        return clazz.isAssignableFrom(clazz);
                    }
                };
            }
            @Override
            public MethodMatcher getMethodMatcher() {
                return new MethodMatcher() {
                    @Override
                    public boolean matches(Method method, Class<?> targetClass) {
                        return "run".equals(method.getName());
                    }

                    @Override
                    public boolean isRuntime() {
                        return false;
                    }

                    @Override
                    public boolean matches(Method method, Class<?> targetClass, Object... args) {
                        return false;
                    }
                };
            }
        };
        MethodBeforeAdvice beforeAdvice = new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] args, Object target) throws Throwable {
                log.info("beforeAdvice, method:{}, args:{}, target:{}", method, args, target);
            }
        };

        AfterReturningAdvice afterReturningAdvice = new AfterReturningAdvice() {
            @Override
            public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
                log.info("afterReturningAdvice, method:{}, args:{}, target:{}, return:{}", method, args, target, returnValue);

            }
        };
        ThrowsAdvice throwsAdvice = new ThrowsAdvice() {
            //            void afterThrowing([Method, args, target], ThrowableSubclass);
//            public void afterThrowing(Method method, Object[] args, Object target, Throwable throwable) {
//                log.error("throwsAdvice, method:{}, args:{}, target:{}, throw:{}", method, args, target, throwable);
//            }
            public void afterThrowing(Method method, Object[] args, Object target, Exception e) {
                log.error("异常通知:" + e.getMessage());
            }
        };

        DefaultPointcutAdvisor beforeAdvisor = new DefaultPointcutAdvisor(pointcut, beforeAdvice);
        DefaultPointcutAdvisor afterReturningAdvisor = new DefaultPointcutAdvisor(pointcut, afterReturningAdvice);
        DefaultPointcutAdvisor throwsAdvisor = new DefaultPointcutAdvisor(pointcut, throwsAdvice);

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addAdvisors(afterReturningAdvisor,throwsAdvisor,beforeAdvisor);
        proxyFactory.setTarget(clazz.newInstance());
//        AopService aopService = (AopService) proxyFactory.getProxy();
//        proxyFactory.setTargetClass(IAopService.class);
//        IAopService aopService = (IAopService) proxyFactory.getProxy();
//        proxyFactory.setTargetClass(clazz);
        T aopService = (T) proxyFactory.getProxy();
        aopService.run();
    }
}
