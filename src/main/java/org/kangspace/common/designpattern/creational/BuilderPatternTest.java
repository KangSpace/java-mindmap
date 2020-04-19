package org.kangspace.common.designpattern.creational;

/**
 * 建造者模式:
 * 将一个复杂对象的构建与展示分离，使得同样的构建过程产生不同的表示
 * 2020/1/6 18:05
 *
 * @author kango2gler@gmail.com
 */
public class BuilderPatternTest {
    static void main(){
        String cat = AnimalBuilder.create()
                .buildName("Tom")
                .buildHead("水汪汪的大眼睛")
                .buildLeg("黑白相间")
                .buildType("猫")
                .build();
        System.out.println(cat);
        String dog = AnimalBuilder.create()
                .buildName("Jerry")
                .buildHead("大眼睛")
                .buildLeg("棕色")
                .buildType("狗")
                .build();
        System.out.println(dog);
    }
    public static void main(String[] args) {
        main();
    }

    /**
     * 动物建造者
     */
    static class AnimalBuilder{
        private String name = "anonymous";
        private String type = "unknow";
        private String head = "no head";
        private String leg = "no legs";
        private String tail = "no tail";
        public AnimalBuilder(){}

        public static AnimalBuilder create(){
            return new AnimalBuilder();
        }

        AnimalBuilder buildName(String name){
            this.name = name;
            return this;
        }
        AnimalBuilder buildHead(String head){
            this.head = head;
            return this;
        }
        AnimalBuilder buildLeg(String leg){
            this.leg = leg;
            return this;
        }
        AnimalBuilder buildType(String type){
            this.type = type;
            return this;
        }
        AnimalBuilder buildTail(String tail){
            this.tail = tail;
            return this;
        }
        String build(){
            return new StringBuilder().append("一个名为: ")
                    .append(this.name)
                    .append("的动物:")
                    .append(type)
                    .append("它有")
                    .append(head)
                    .append(",")
                    .append(leg)
                    .append("样的腿，")
                    .append(tail)
                    .append("样的尾巴")
                    .toString();
        }
    }


}
