package org.kangspace.common.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 2019/5/28 10:28
 *
 * @author kango2gler@gmail.com
 */
public class Temp {
    private Integer eqId;
    private Integer count;
    private Integer dunwei;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getDunwei() {
        return dunwei;
    }

    public void setDunwei(Integer dunwei) {
        this.dunwei = dunwei;
    }

    public Temp() {
    }

    public Temp(Integer eqId, Integer count, Integer dunwei) {
        this.eqId = eqId;
        this.count = count;
        this.dunwei = dunwei;
    }

    public static void main(String[] args) {
        Map<Integer, Temp> map = new HashMap();
        int n = 2;
        int countSum=0;
        int dunweiSum = 0;
        for (int i=0;i<n;i++){
            countSum += 0;
            dunweiSum +=0;
            map.put(0,new Temp(0,0,0));
        }
        for(Iterator<Integer>it=map.keySet().iterator();((Iterator) it).hasNext();){
            Temp temp = map.get(it);
            temp.setCount(countSum);
            temp.setDunwei(dunweiSum);
        }
    }
}
