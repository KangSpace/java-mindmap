package org.kangspace.common.algorithms.datastructure.tree.test;

import org.kangspace.common.algorithms.datastructure.tree.AVLBinaryTree;
import org.kangspace.common.algorithms.datastructure.tree.AVLBinaryTreePrinter;

import java.util.Arrays;

/**
 * 平衡二叉树测试
 *
 */
public class AVLBinaryTreeTest {
    static void main(){
        Integer[] data = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        System.out.println(Arrays.toString(data));
        AVLBinaryTree intBinaryTree = new AVLBinaryTree();
        for (Integer i : data) {
            intBinaryTree.insert(i);
        }
        AVLBinaryTree.AVLNode root =intBinaryTree.getRoot();
        System.out.print("树深度:");
        System.out.println(intBinaryTree.length(root));
        System.out.print("根对象:");
        System.out.println(intBinaryTree.getRoot());

        new AVLBinaryTreePrinter<>(intBinaryTree).printHorizontalTree2();
        System.out.println("");

        System.out.println();




    }


    public static void main(String[] args) {
        main();
    }

}
