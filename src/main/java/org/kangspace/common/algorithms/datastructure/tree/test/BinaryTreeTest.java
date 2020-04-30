package org.kangspace.common.algorithms.datastructure.tree.test;

import org.kangspace.common.algorithms.datastructure.tree.BinaryTree;
import org.kangspace.common.algorithms.datastructure.tree.BinaryTreePrinter;

import java.util.Arrays;

/**
 * 二叉树
 * 二叉树的存储结构:
 * 1. 顺序存储
 * 2. 二叉链表存储
 * 3. 三叉链表存储
 * 2020/4/28 9:46
 */
public class BinaryTreeTest {
    static void main(){
        Integer[] data = new Integer[]{6,4,8,3,5,7,9};
        System.out.println(Arrays.toString(data));
        BinaryTree<Integer> intBinaryTree = new BinaryTree<>();
        for (Integer i : data) {
            intBinaryTree.insert(i);
        }
        BinaryTree<Integer>.Node<Integer> root =intBinaryTree.getRoot();
        System.out.print("树深度:");
        System.out.println(intBinaryTree.length());
        System.out.print("根对象:");
        System.out.println(intBinaryTree.getRoot());

        //查找元素
        System.out.print("查找值为6的节点:" );
        BinaryTree<Integer>.Node<Integer> findNode6 =  intBinaryTree.get(root,6);
        System.out.println("结果为: "+ findNode6 != null?findNode6.getValue()+" : "+findNode6:null);
        System.out.println("");

        //删除元素
        System.out.print("删除值为5的节点:" );
//        System.out.println("结果为: "+ intBinaryTree.remove(5));

        System.out.print("树深度:");
        System.out.println(intBinaryTree.length());

//        System.out.print("查找值为5的节点:" );
//        BinaryTree<Integer>.Node<Integer> findNode5 =  intBinaryTree.get(root,5);
//        System.out.println("结果为: "+ (findNode5 != null?findNode5.getValue()+" : "+findNode5:null));

        System.out.println("");

        System.out.print("打印树结构:");
        System.out.println("-水平树(前序遍历):");
        new BinaryTreePrinter<>(intBinaryTree).printHorizontalTree();

        System.out.println("");

        System.out.print("打印树结构:");
        System.out.println("-水平树(中序遍历):");
        new BinaryTreePrinter<>(intBinaryTree).printHorizontalTree2();

        System.out.print("打印树结构:");
        System.out.println("-垂直树:");
        new BinaryTreePrinter<>(intBinaryTree).printTree();

        System.out.println("\n");

        System.out.print("前序遍历:");
        intBinaryTree.prevOrderIterate(root);
        System.out.println();
        System.out.print("中序遍历:");
        intBinaryTree.midOrderIterate(root);
        System.out.println();
        System.out.print("后序遍历:");
        intBinaryTree.postOrderIterate(root);
        System.out.println();
        System.out.print("层序遍历:");
        intBinaryTree.levelOrderIterate(root);
        System.out.println();




    }
    public static void main(String[] args) {
        main();
    }

}
