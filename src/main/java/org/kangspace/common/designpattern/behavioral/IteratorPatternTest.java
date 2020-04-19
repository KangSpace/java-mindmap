package org.kangspace.common.designpattern.behavioral;

/**
 * 迭代器模式
 * 概述: 提供一种方法顺序访问一个聚合对象中的各种元素，而又不暴露该对象的内部表示。
 * 适用性:
 *      1. 访问一个聚合对象而无需暴露它的内部表示
 *      2. 支持聚合对象的多种遍历
 *      3. 为遍历不用的聚合结构提供一个统一的接口(即，支持多态迭代)
 * 参与者:
 *      1. Iterator 迭代器定义访问和遍历元素的接口
 *      2. ConcreterIterator 具体迭代器实现迭代器接口。
 *                           对该聚合遍历时跟踪当前位置
 *      3. Aggregate 聚合定义创建相应聚合对象的接口
 *      4. ConcreteAggregate 具体聚合实现创建相应迭代器的接口，返回ConcreteIterator
 * @author kango2gler@gmail.com
 */
public class IteratorPatternTest {
    /**
     * 可迭代对象接口
     * 定义创建迭代器操作接口
     */
    interface Iterable<T>{
        Iterator<T> iterator();
    }

    /**
     * 迭代器接口
     * 定义访问和遍历元素接口
     * @param <T>
     */
    interface Iterator<T>  {
        T first();
        T last();
        T next();
        boolean hasNext();
    }

    /**
     * 聚合对象
     */
    static class Aggregator<T> implements Iterable<T> {
        private Object[] objs;
        private int count;
        public Aggregator(){
            this.objs = new Object[100];
        }

        @Override
        public Iterator<T> iterator() {
            return new AggregatorIterator<>();
        }

        public void add(T t) {
            objs[count] = (Object)t;
            count++;
        }

        public T get(int i) {
            if(i>=count || i<0) throw new ArrayIndexOutOfBoundsException();
            return (T)objs[i];
        }

        public int size() {
            return count;
        }

        /**
         * 具体的迭代器实现
         * 实现迭代接口,记录迭代位置
         */
        private class AggregatorIterator<T> implements Iterator<T>{
            private int position = -1;
            private Object[] data = objs;
            @Override
            public T first() {
                return (T)data[0];
            }

            @Override
            public T last() {
                return (T)data[size()-1];
            }

            @Override
            public T next() {
                return (T)data[position];
            }

            @Override
            public boolean hasNext() {
                return ++position<size();
            }
        }
    }


    static void main(){
        Aggregator<Integer> list = new Aggregator<>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);

        //第一种迭代 iterator
        System.out.println("===========第一种迭代 iterator===========");
        Iterator it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        //第二种迭代 for
        System.out.println("===========第二种迭代 for===========");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

    }
    public static void main(String[] args) {
        main();
    }
}
