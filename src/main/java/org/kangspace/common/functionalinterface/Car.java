package org.kangspace.common.functionalinterface;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * 2019/12/10 11:24
 *
 * @author kango2gler@gmail.com
 */
public class Car {
    public static Car create(final Supplier<Car> supplier) {
        return supplier.get();
    }

    public static void collide(final Car car) {
        System.out.println("Collided " + car.toString());
    }

    public void follow(final Car another) {
        System.out.println("Following the " + another.toString());
    }

    public void repair() {
        System.out.println("Repaired " + this.toString());
    }

    public static void main(String[] args) {
        //类构造器引用
        final Car car = Car.create(Car::new);
        final Car car2 = Car.create(() -> new Car());
        List<Car> cars = Arrays.asList(car);
        List<Car> cars2 = Arrays.asList(car2);
        //类静态方法引用
        cars.forEach(Car::collide);
        cars2.forEach((c)->Car.collide(c));

        //特定类的任意方法引用
        cars.forEach(Car::repair);
        cars2.forEach((c)->c.repair());

        //特定实例的任意方法引用
        cars.forEach(Car.create(Car::new)::follow);
        Car police = Car.create(Car::new);
        cars.forEach(police::follow);
        cars2.forEach((c)->police.follow(c));

    }
}
