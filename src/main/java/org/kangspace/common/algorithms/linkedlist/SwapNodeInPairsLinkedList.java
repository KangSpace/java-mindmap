package org.kangspace.common.algorithms.linkedlist;

/**
 * 链表中反转2个相邻节点
 *
 * @author kango2gler@gmail.com
 * @since 2022/7/6
 */
public class SwapNodeInPairsLinkedList {


    /**
     * 反转链表相邻元素
     * src: 1,2,3,4,5
     * desc: 2,1,3,5,4
     * 实现逻辑: 2个相邻节点相互对调, 使用递归，将第二个节点与第一个节点对调，递归下去
     *
     * @param head
     */
    public static LinkedNode swapPairs(LinkedNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 每组第二个节点
        LinkedNode secondNode = head.next;
        // 将每组第一个节点的next指向下一组第一个节点
        head.next = swapPairs(head.next.next);
        // 每组第二个节点指向第一个节点
        secondNode.next = head;
        return secondNode;
    }

    /**
     * TODO 反转链表相邻元素
     * src: 1,2,3,4,5
     * desc: 2,1,3,5,4
     * 实现逻辑: 2个相邻节点相互对调,
     *
     * @param head
     */
    public static LinkedNode swapPairs2(LinkedNode head) {
//        LinkedNode pre = new LinkedNode();
//        LinkedNode curr = head;
////        pre.next = curr;
//        LinkedNode nextLoopNode = null;
 /*       // 当前的2个节点互相不为空
        while (curr != null && curr.next != null) {
            // 存储下一轮节点
            nextLoopNode = curr.next.next;
            pre.next = curr.next;
            curr.next.next = curr;
            curr.next = nextLoopNode;

//            pre = curr;
            curr = nextLoopNode;
        }*/
        //当前遍历结点
        LinkedNode cur = head.next;
        //当前结点的前驱结点
        LinkedNode pre = head;
        //当前结点后继结点的后继结点
        LinkedNode next = null;
        while (cur != null && cur.next != null) {
            next = cur.next.next;
            pre.next = cur.next;
            cur.next.next = cur;
            cur.next = next;
            pre = cur;
            cur = next;
        }
        return head;
    }

    public static void main(String[] args) {
        LinkedNode linkList = new LinkedNode(1, new LinkedNode(2), new LinkedNode(3), new LinkedNode(4), new LinkedNode(5));
        System.out.print("src: ");
        linkList.print();
        System.out.println();
        LinkedNode linkList2 = swapPairs(linkList);
        System.out.print("reversed: ");
        linkList2.print();
        System.out.println();
        LinkedNode linkList2Src = new LinkedNode(1, new LinkedNode(2), new LinkedNode(3), new LinkedNode(4), new LinkedNode(5));
        linkList2 = swapPairs2(linkList2Src);
        System.out.print("reversed2: ");
        linkList2.print();
        System.out.println();
    }
}
