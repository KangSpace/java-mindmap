package org.kangspace.common.algorithms.linkedlist;

/**
 * 链表反转
 * @author kango2gler@gmail.com
 * @since 2022/7/6
 */
public class ReverseLinkedList {
    /**
     * 反转链表
     * src: 1,2,3,4,5
     * desc: 5,4,3,2,1
     * 实现逻辑: 每个节点指向其前置节点(同头插法)
     *
     * @param head
     */
    public static LinkedNode reverse(LinkedNode head) {
        LinkedNode curr =  head, prev = null, next = null;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
    /**
     * 反转链表2
     * src: 1,2,3,4,5
     * desc: 5,4,3,2,1
     * 实现逻辑: 新建链表，头插法
     *
     * @param head
     */
    public static LinkedNode reverse2(LinkedNode head) {
        LinkedNode curr =  head, newNodeNext= null, next;
        while (curr != null) {
            next = curr.next;
            curr.next = newNodeNext;
            newNodeNext = curr;
            curr = next;
        }
        return newNodeNext;
    }

    public static void main(String[] args) {
        LinkedNode linkList = new LinkedNode(1, new LinkedNode(2), new LinkedNode(3), new LinkedNode(4), new LinkedNode(5));
        System.out.print("src: ");
        linkList.print();
        System.out.println();
        LinkedNode linkList2 = reverse2(linkList);
        System.out.print("reversed: ");
        linkList2.print();
        System.out.println();
    }
}
