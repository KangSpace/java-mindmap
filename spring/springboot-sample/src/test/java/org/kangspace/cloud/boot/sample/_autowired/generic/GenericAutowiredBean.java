package org.kangspace.cloud.boot.sample._autowired.generic;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 泛型Bean
 * @author kango2gler@gmail.com
 * @since 2022/6/19
 */
@Configuration
@Data
public class GenericAutowiredBean {
    @Autowired
    private IDao<User> userDao;
    @Autowired
    private IDao<Product> productDao;

    @Bean
    public IDao userDao() {
        return new UserDao();
    }
    @Bean
    public IDao productDao() {
        return new ProductDao();
    }


    /**
     * 带泛型接口
     * @param <T> T
     */
    public interface IDao<T>{ }

    /**
     * UserDao 实现带User泛型的IDao
     */
    public static class UserDao implements IDao<User> { }

    /**
     * ProductDao 实现带Product泛型的IDao
     */
    public static class ProductDao implements IDao<Product> { }

    public static class User{ }
    public static class Product{ }
}
