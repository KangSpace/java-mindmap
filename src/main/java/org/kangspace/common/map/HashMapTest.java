package org.kangspace.common.map;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 2019/12/24 10:21
 * HashMap: <br/>
 * <pre>
 * HashMap 结构
 * 1. 数组 + 链表(单向链表) 即 Node[] table
 * Node<K,V>结构
 *   - hash
 *   - K k
 *   - V v
 *   - Node<K,V> next
 * 2. 数组 + 红黑树 即 TreeNode[] table
 *
 * HashMap初始化(1.8):
 * 1. 创建HashMap
 * new HashMap()
 *   loadFactor = 0.75
 * new HashMap(int initialCap)
 *  = new HashMap(initialCap, 0.75)
 * new HashMap(int initialCap, int loadFactor)
 *    loadFactor = 0.75
 *    threshold = tableSize(initialCap) 即 >= initialCap 的最小2的幂次数,
 *      如 initialCap = 17, 则>=17的最小2次幂数为 2^5 = 32;
 *          initialCap = 16, 则>=16的最小2次幂数为 2^4 = 16;
 * 数组容量不初始化且为null。
 *
 *2. 赋值 putVal(int hash,Object key)
 * a. 当 首次插入数据时, if ((tab = table) == null || (n = tab.length) == 0) 即 table 未初始化,或table当前为空时, 执行 resize() 初始化操作
 *    场景: new HashMap() 后首次插入
 * b. 使用(n - 1) & hash计算出数组下标
 *    - 若 table[(n - 1) & hash] == null, 则在该数组位置创建新的Node节点
 *    - table[(n - 1) & hash] != null, 则表示存在链表，则循环链表，检查key是否存在（先检查头结点是否为当前key节点，然后再遍历）
 *
 *    其中若key不存在, 则在链表中插入新Node,
 *    若链表长度>=8时,进行树化(TreeNode), 树化时, 若数组容量 < 64时,触发resize()扩容
 *    - TreeNode<K,V> extends LinkedHashMap.Entry<K,V> 红黑树。
 * c. 操作已经获取到的节点值, 若!onlyIfAbsent || oldValue == null 则新值替换旧值, 并调用afterNodeAccess方法,用于LinkedHashMap
 * d. modCount ++ ; ++size; 并判断 size 是否 > threshold
 *    若 ++size > threshold, 则进行resize()
 * e. 执行 afterNodeInsertion() // 用于LinkedHashMap
 *
 *3. resize()
 * a. table 为空时, 即首次插入数据, oldCap,newThr 都为空, 则按初始化配置 newCap = 16, newThr = 16 * 0.75 = 12
 *    场景: new HashMap()时(threshold为空) 首次插入数据
 * b. oldCap >0,
 *    - 若oldCap >= MAXIMUM_CAPACITY, threshold = Integer.MAX_VALUE;
 *    - 若oldCap < MAXIMUM_CAPACITY, (newCap = oldCap << 1) < MAXIMUM_CAPACITY && oldCap >= DEFAULT_INITIAL_CAPACITY
 *       则 newThr = oldThr << 1; 即容量设置为原容量的2倍, threshold也设置为原threshold的2倍
 *    场景: 非首次插入数据
 * c. oldCap <=0 && oldThr > 0 时
 *    场景: new HashMap(initialCap) 或 new HashMap(initialCap, loadFactor),即 table 为空, threshold有值的情况, 容量即为 threshold容量
 *    即为大于初始容量的最小2的幂次倍容量.
 * 然后按新容量创建数组: Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
 * 若旧数组存在数据，则遍历将旧数组数据复制到新数组(参见: https://segmentfault.com/a/1190000015812438 , https://www.programmersought.net/article/344852001.html):
 * (
 *   新数组为旧数组的2倍:  旧数组中的元素必定在新数组中的j 或 j+oldCap 的位置
 *   即 e.hash & oldCap == 0 和 e.hash & oldCap == 1
 *
 *  )
 *  Node<K,V> loHead = null, loTail = null;
 *  Node<K,V> hiHead = null, hiTail = null;
 *  Node<K,V> next;
 *  do {
 *      next = e.next;
 *      if ((e.hash & oldCap) == 0) {
 *          if (loTail == null)
 *              loHead = e;
 *          else
 *              loTail.next = e;
 *          loTail = e;
 *      }
 *      else {
 *          if (hiTail == null)
 *              hiHead = e;
 *          else
 *              hiTail.next = e;
 *          hiTail = e;
 *      }
 *  } while ((e = next) != null);
 *  if (loTail != null) {
 *      loTail.next = null;
 *      newTab[j] = loHead;
 *  }
 *  if (hiTail != null) {
 *      hiTail.next = null;
 *      newTab[j + oldCap] = hiHead;
 *  }
 * </pre>
 *
 * LinkedHashMap<br/>
 * <pre>
 * LinkedHashMap初始化(1.8):
 * 1. 创建HLinkedHashMap
 * new LinkedHashMap(), new LinkedHashMap(int initialCap), new LinkedHashMap(int initialCap, float loadFactor),
 *  new LinkedHashMap(int initialCap, float loadFactor, boolean accessOrder),
 * 构造方法执行同HashMap, 加上accessOrder = false;
 * accessOrder: true 表示遍历时使用访问顺序, false表示遍历时使用插入顺序
 *
 * 与HashMap不同的是:
 * 1. 插入/删除, 查询后对应执行 afterNodeAccess(e); afterNodeInsertion(boolean evict); void afterNodeRemoval(Node<K,V> e) ;
 * </pre>
 *
 * @author kango2gler@gmail.com
 */
public class HashMapTest {
    static void main(){
        int initialCapacity = 16;
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
