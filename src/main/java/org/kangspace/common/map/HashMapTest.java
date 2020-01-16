package org.kangspace.common.map;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 2019/12/24 10:21
 *
 * @author kangxuefeng@etiantian.com
 */
public class HashMapTest {
    static void main(){
        HashMap map = new HashMap();
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
    }

    public static void main(String[] args) {
        main();
    }
}
