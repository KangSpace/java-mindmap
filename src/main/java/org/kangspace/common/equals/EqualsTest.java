package org.kangspace.common.equals;

/**
 * 2020/1/6 18:05
 *
 * @author kango2gler@gmail.com
 */
public class EqualsTest {
    private int i;

    public EqualsTest () {
    }

    public EqualsTest (int i) {
        this.i = i;
    }

    /**
     * 实现equals原则:
     * 1. 自反省 reflexive ,非null x , x.equals(x)
     * 2. 对称性 symmetric ,非null x,y , x.equals(y),则 y.equals(x)
     * 3. 传递性 transtive ,非null x,y,z , x.equals(y),y.equals(z),则 x.equals(z)
     * 4. 一致性 consistent , 非null x,y , 在不修改x,y的情况下,任意调用x.equals(y)返回true
     * 5. 非null x , x.equals(null) 返回false;
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        return this == obj || (obj instanceof EqualsTest && this.hashCode() == ((EqualsTest)obj).hashCode() && this.i == ((EqualsTest)obj).i);
    }

    @Override
    public int hashCode() {
        return i<<2;
    }

    static void main(){
        EqualsTest t1 = new EqualsTest(1);
        EqualsTest t2 = new EqualsTest(1);
        System.out.println(t1.equals(t2));
        System.out.println(t2.equals(t1));
        System.out.println("======= hashCode =======");
        System.out.println(t1.hashCode());
        System.out.println(t2.hashCode());
    }
    public static void main(String[] args) {
        main();
    }
}
