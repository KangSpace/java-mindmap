package org.kangspace.common.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * <pre>
 * MapStruct:(JDK1.8+)
 * MapStruct是一个Java注释处理器，用于为Java Bean类生成类型安全和高性能的映射器。
 *
 * 与在运行时工作的映射框架相比，MapStruct具有以下优点：
 * 1. 通过使用普通方法调用而不是反射来快速执行
 * 2. 编译时类型安全。只能映射彼此映射的对象和属性，因此不会将订单实体意外映射到客户DTO等。
 * 3. 独立的代码-没有运行时依赖
 * 示例代码:
 * <code>
 * &#64Mapper
 * public interface CarMapper {
 *
 *     CarMapper INSTANCE = Mappers.getMapper( CarMapper.class );
 *
 *     &#64Mapping(source = "numberOfSeats", target = "seatCount")
 *     CarDto carToCarDto(Car car);
 * }
 * </code>
 * 实现原理:
 *    1. 在代码编译期间扫描&#64org.mapstruct.Mapper注解的接口interface UserMapStruct,并生成对应接口的实现类;
 *       并在实现类中实现定义的各个转换方法，并自动转换相同属性名的属性(基础类型会做数据类型转换),同时根据
 *       &#64Mapping(source = "userSex",target = "sex") 注解设置指定的属性转换;
 *       实现代码示例如下:
 *       <code>
 *       public UserBeanDto toUserBeanDto(UserBean userBean) {
 *         if (userBean == null) {
 *             return null;
 *         } else {
 *             UserBeanDto userBeanDto = new UserBeanDto();
 *             if (userBean.getUserSex() != null) {
 *                 userBeanDto.setSex(String.valueOf(userBean.getUserSex()));
 *             }
 *
 *             userBeanDto.setUserId(userBean.getUserId());
 *             userBeanDto.setUserName(userBean.getUserName());
 *             return userBeanDto;
 *         }
 *     }
 *     </code>
 *    2. 在CustomMapStruct接口中创建静态属性<code>UserMapStruct INSTANCE = Mappers.getMapper(UserMapStruct.class);</code>
 *    3. 2中的Mappers.getMapper(UserMapStruct.class);会根据UserMapStruct.class的类名UserMapStruct.class加载以Impl结尾的实现类,
 *       见{@link Mappers#IMPLEMENTATION_SUFFIX},{@link Mappers#doGetMapper(Class, ClassLoader)}
 *    4. 使用时通过3中的 {@link #INSTANCE} 静态属性访问各个转换方法。
 * </pre>
 * @see <a href="https://github.com/mapstruct/mapstruct/">https://github.com/mapstruct/mapstruct/</a>
 */
@Mapper
public interface UserMapStruct{
    UserMapStruct INSTANCE = Mappers.getMapper(UserMapStruct.class);

    @Mapping(source = "userSex",target = "sex")
    Beans.UserBeanDto toUserBeanDto(Beans.UserBean userBean);
    @Mapping(source = "sex",target = "userSex")
    Beans.UserBean fromUserBeanDto(Beans.UserBeanDto userBeanDto);
    Beans.UserBean fromUserBeanDto2(Beans.UserBeanDto2 userBeanDto);
    Beans.UserBeanDto2 toUserBeanDto2(Beans.UserBean userBean);
}