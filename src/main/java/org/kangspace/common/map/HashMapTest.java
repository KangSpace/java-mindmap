package org.kangspace.common.map;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 2019/12/24 10:21
 *
 * @author kango2gler@gmail.com
 */
public class HashMapTest {
    static void main(){
        int initialCapacity = 18;
        HashMap map = new HashMap(initialCapacity);
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
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("1",1);
        linkedHashMap.put("2",1);
        System.out.println(map.put("b",1));
        System.out.println(map.put("b",2));

        Map unmodifiableMap = Collections.unmodifiableMap(map);
        System.out.println(unmodifiableMap);
        System.out.println("----------------------------------------");

        int nodes = getHashMapTableSize(map);
        int size = map.size();
        System.out.println("map initialCapacity:"+initialCapacity);
        System.out.println("map size:"+size+"\nmap table size:"+nodes);
        //trigger resizing , ++size > threshold
        int addNewNodeCnt = 0;
        for (int i=map.size(); i <= nodes * 0.75;i++ ) {
            map.put(i, i);
            addNewNodeCnt++;
        }
        //re-get table size
        nodes = getHashMapTableSize(map);
        "".split("");
        System.out.println("-- map resize: add new node count:"+addNewNodeCnt+" , old size:"+size);
        System.out.println("map new size:"+map.size()+"\nmap new table size:"+nodes);

    }

    static int getHashMapTableSize(HashMap map){
        int nodes = 0;
        Field field = null;
        try {
            field = HashMap.class.getDeclaredField("table");
            field.setAccessible(true);
            String nodeString = "Node";
            Class nodeClass = null;
            for (Class<?> declaredClass : HashMap.class.getDeclaredClasses()) {
                if (declaredClass.getName().indexOf(nodeString)>-1) {
                    nodeClass = declaredClass;
                }
            }
            Object nodeList = field.get(map);
            nodes = Array.getLength(field.get(map));
            System.out.println("\ttable node:");
            for (int i = 0; i < nodes; i++) {
                Object node = Array.get(nodeList,i);
                if (node != null) {
                    System.out.println("\t node.getClass().getName():"+node.getClass().getName()+" value:"+node);
                }
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return nodes;
    }
    public static void main(String[] args) {
        main();
    }
}
