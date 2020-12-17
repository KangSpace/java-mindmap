package org.kangspace.common.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <pre>
 *
 * </pre>
 *
 * @author kango2gler@gmail.com
 * @date 2020/11/17 15:35
 */
public class HashMapResizeTest {
    static void main(){
        HashMap map = new HashMap(2);
        map.put("1",1);
        map.put("2",1);
        map.put("3",1);
        map.put("4",1);
        map.put("5",1);
        map.put("6",1);
        map.put("7",1);
        map.put("8",1);
        map.put("9",1);
        map.put("10",1);
        map.put("11",1);
        map.put("12",1);
        map.put("13",1);
        map.put("14",1);
        map.put("15",1);
        map.put("16",1);
        map.put("17",1);
        map.put("a",1);
        listHandle();
    }

    /**
     * 模拟链表在多线程下的操作
     * 需多线程才能触发
     */
    public static void listHandle(){
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        List list2 = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            list2.add(i+5);
            list2.add(list.get(i));
        }
        int i = 0;
        for (int j = 0; j <list.size() ; j++) {
            if (i == 2) {
                list = list2;
            }
            Object o = list.get(j);
            System.out.println(o);
            i++;
        }
    }

    public static void listHandle2(){
        List<Integer> list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        for (Integer i : list) {
            System.out.println(i);
        }
        for (Object o : list) {
            System.out.println(o);
        }
    }

    public static void main(String[] args) {
        main();
    }
}
