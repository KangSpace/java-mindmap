package org.kangspace.common.algorithms.linkedlist;

import lombok.Data;

/**
 * 链表节点
 * @author kango2gler@gmail.com
 * @since 2022/7/6
 */
@Data
public class LinkedNode {
    public Object value;
    public LinkedNode next;
    public void addNext(LinkedNode node) {
        this.next = node;
    }

    public LinkedNode() {
    }
    public LinkedNode(Object value) {
        this.value = value;
    }
    public LinkedNode(Object value, LinkedNode... next) {
        this(value);
        if (next != null && next.length > 0) {
            this.next = next[0];
            LinkedNode n = this.next;
            for (int i = 1; i < next.length; i++) {
                n.next = next[i];
                n = n.next;
            }
        }
    }
    public void print() {
        System.out.print(this.value+"\t");
        LinkedNode node = this;
        while (node.next != null) {
            System.out.print(node.next.value+"\t");
            node = node.next;
        }
    }
}
