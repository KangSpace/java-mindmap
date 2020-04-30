package org.kangspace.common.algorithms.datastructure.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树(二叉链表存储)
 * 二叉树的存储结构:
 * 1. 顺序存储
 * 2. 二叉链表存储
 * 3. 三叉链表存储
 * 2020/4/28 14:42
 */
public class BinaryTree<T> {
    /**
     * the ROOT
     */
    private Node<T> root;

    /**
     * 插入数据
     *
     * @param value 值
     * @return 新插入的节点对象
     */
    public Node<T> insert(T value) {
        if (root == null) {
            return initRoot(value);
        }
        return insertToNode(root, value);
    }

    public Node<T> initRoot(T value) {
        return root = insertToNode(root, value);
    }

    public int length() {
        return length(root);
    }
    /**
     * 获取数的深度
     * @return
     */
    public int length(Node<T> root) {
        return root == null ?0: 1 +Math.max(length(root.left),length(root.right));
    }

    /**
     * 通过value查找节点
     * @param value
     * @return
     */
    public Node<T> get(Node<T> curr,T value) {
        if (curr == null) {
            return null;
        }
        if (curr.compare(value) == 0) {
            return curr;
        }
        return curr.compare(value) > 0?get(curr.left, value):get(curr.right, value);

    }

    /**
     *
     * @param value
     * @return root
     */
    public Node<T> remove(T value) {
        return root = remove(root, value);
    }

    /**
     * 删除节点(节点可能需要重组)
     * @param curr
     * @param value
     * @return
     */
    private Node<T> remove(Node<T> curr,T value) {
        if (curr == null) {
            return null;
        }
        //节点重组
        if (curr.compare(value) == 0) {
            // Node to delete found
            // 一个节点没有子节点
            if (curr.left == null && curr.right == null) {
                return null;
            }
            //一个节点只有一个子节点-在父节点中，我们用唯一的子节点替换该节点。
            if (curr.right == null) {
                return curr.left;
            }
            if (curr.left == null) {
                return curr.right;
            }
            //一个节点有两个孩子 –这是最复杂的情​​况，因为它需要对树进行重组
            //从右结点中找到最小值的节点
            T smallestValue = (T) findSmallestValue(curr.right);
            curr.value = smallestValue;
            //将最小值节点删除
            curr.right = remove(curr.right, smallestValue);
            return curr;
        }
        if (curr.compare(value)>0) {
            curr.left = remove(curr.left, value);
            return curr;
        }
        curr.right = remove(curr.right, value);
        return curr;
    }

    /**
     * 找出结点中最小值的结点
     * @param root
     * @return
     */
    private T findSmallestValue(Node<T> root) {
        return root.left == null ? root.value : (T)findSmallestValue(root.left);
    }

    /**
     * 前序遍历(深度优先遍历)
     */
    public void prevOrderIterate(Node<T> curr) {
        if (curr == null) {
            return;
        }
        System.out.print(curr.getValue() + ",");
        prevOrderIterate(curr.left);
        prevOrderIterate(curr.right);
    }

    /**
     * 中序遍历(深度优先遍历)
     */
    public void midOrderIterate(Node<T> curr) {
        if (curr == null) {
            return;
        }
        midOrderIterate(curr.left);
        System.out.print(curr.getValue() + ",");
        midOrderIterate(curr.right);
    }

    /**
     * 后序遍历(深度优先遍历)
     */
    public void postOrderIterate(Node<T> curr) {
        if (curr == null) {
            return;
        }
        postOrderIterate(curr.left);
        postOrderIterate(curr.right);
        System.out.print(curr.getValue() + ",");
    }

    /**
     * 层序遍历(广度优先遍历)
     * 层序遍历需要使用到队列数据结构
     */
    public void levelOrderIterate(Node<T> root) {
        if (root == null) {
            return;
        }
        //add(队列满,抛异常)/offer(队列满,返回false)/put(队列满,阻塞)
        //remove(从头部移除数据,队列空,抛异常)/poll(取数据,队列空,返回false)/take(取数据,队列空,阻塞)
        //element(从队列头查询元素,队列空，抛异常)/peek(队列为空,返回null)
        Queue<Node<T>> queue = new LinkedList();
        ((LinkedList<Node<T>>) queue).add(root);
        Node<T> front;
        while (!queue.isEmpty()) {
            front = queue.remove();
            if (front.left != null) {
                ((LinkedList<Node<T>>) queue).add(front.left);
            }
            if (front.right != null) {
                ((LinkedList<Node<T>>) queue).add(front.right);
            }
            System.out.print(front.getValue()+",");
        }
    }

    /**
     * 将数据插入到指定节点
     * 使树保持排序，需遵循:
     * 1. 如果新节点的值小余当前节点的值，则转到左节点
     * 2. 如果新节点的值大于当前节点的值，则转到正确的子节点
     * 3. 如果当前节点为null时，到达了叶子节点，把新节点插入该位置
     *
     * @param value 值
     * @param curr  当前节点
     * @return 新插入的节点对象
     */
    private Node<T> insertToNode(Node<T> curr, T value) {
        if (curr == null) {
            return new Node<>(value);
        }
        if (curr.compare(value) > 0) {
            curr.left = insertToNode(curr.left, value);
        } else if (curr.compare(value) < 0) {
            curr.right = insertToNode(curr.right, value);
        }
        //若值相同,则节点已存在
        return curr;
    }


    public Node<T> getRoot() {
        return root;
    }

    public void setRoot(Node<T> root) {
        this.root = root;
    }

    /**
     * 二叉树节点:
     * 由一个数据域和2个指针域组成的节点
     *
     * @param <T>
     * @author kangxuefeng
     */
    public class Node<T> implements Comparable<Node<T>> {
        private T value;
        private Node left;
        private Node right;

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Node() {
        }

        public Node(T value) {
            this.value = value;
        }

        @Override
        public int compareTo(Node<T> o) {
            if (o == null || o.value == null) {
                return -1;
            }
            T val = o.value;
            return compare(val);
        }

        public int compare(T val) {
            if (val instanceof Integer) {
                return ((Integer) this.getValue() - (Integer) val);
            } else if (val instanceof Short) {
                return ((Short) this.getValue() - (Short) val);
            } else if (val instanceof Long) {
                Long compare = ((Long) this.getValue() - (Long) val);
                return compare > 0 ? 1 : compare < 0 ? -1 : 0;
            }
            return 0;
        }
    }

}
