package org.kangspace.common.algorithms.datastructure.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 打印树
 * 2020/4/29 11:03
 */
public class AVLBinaryTreePrinter<T> {
    final String NEWLINE_FLAG = "\n";
    final String LEFT_SUBTREE_FLAG = "├──";
    final String LEFT_SUBTREE_FLAG2 = "┌--";
    final String RIGHT_SUBTREE_FLAG = "└--";
    final String PADDING_FLAG = "丨";

    final String TREE_LEFT_SUBTREE_FLAG = "/";
    final String TREE_RIGHT_SUBTREE_FLAG = "\\";
    final String TREE_PADDING = "  ";
    private AVLBinaryTree tree;

    /**
     * 打印水平树2-中序遍历
     */
    public void printHorizontalTree2() {
        System.out.println(inOrderPrint(tree.getRoot(),new StringBuffer(),"",""));
    }

    public String inOrderPrint(AVLBinaryTree.AVLNode root, StringBuffer sb, String pointer, String parentPadding) {
        if (root == null) {
            return "";
        }
        String paddings = getPadding(level(root))+parentPadding;
        inOrderPrint(root.getLeft(),sb,LEFT_SUBTREE_FLAG2,paddings);

        sb.append(paddings+" ");
        sb.append(pointer).append(root.getValue()).append(NEWLINE_FLAG);

        inOrderPrint(root.getRight(),sb,RIGHT_SUBTREE_FLAG,paddings);
        return sb.toString();
    }

    public String getPadding(int level) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < level; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }


    public List<List<Integer>> getTreeNodes(AVLBinaryTree.AVLNode root) {
        if (root == null) {
            return null;
        }

        List<List<Integer>> arrs = new ArrayList<>();
        List<Integer> levelArr = new ArrayList<>();

        LinkedList<AVLBinaryTree.AVLNode> queue = new LinkedList<>();
        queue.add(root);
        AVLBinaryTree.AVLNode nextNode;
        int lastLevel = 1;
        while (!queue.isEmpty()) {
            nextNode = queue.poll();

            int currLevel = level(nextNode);
            if ( lastLevel != currLevel) {
                lastLevel = currLevel;
                arrs.add(levelArr);
                levelArr = new ArrayList<>();
            }
            levelArr.add(nextNode.getValue());
            int hasNull = 0;
            if (nextNode.getLeft() != null) {
                queue.add(nextNode.getLeft());
            }
            if (nextNode.getRight() != null) {
                queue.add(nextNode.getRight());
            }
        }
        arrs.add(levelArr);
        return arrs;
    }

    public int level(AVLBinaryTree.AVLNode curr) {
        return level(tree.getRoot(), curr, 0);

    }

    public int level(AVLBinaryTree.AVLNode root, AVLBinaryTree.AVLNode curr, int level) {
        if (root == null) {
            return 0;
        }
        ++level;
        if (root.equals(curr)) {
            return level;
        }
        if (root.compareTo(curr) > 0) {
            return level(root.getLeft(), curr, level);
        }
        if (root.compareTo(curr) < 0) {
            return level(root.getRight(), curr, level);
        }
        return 0;
    }


    /**
     * 前序遍历-根节点打印
     *
     * @param root
     * @return
     */
    private String preOrderRootPrint(BinaryTree<T>.Node<T> root) {
        if (root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(root.getValue()).append(NEWLINE_FLAG);

        String pointerForRight = RIGHT_SUBTREE_FLAG;
        String pointerForLeft = (root.getRight() != null) ? LEFT_SUBTREE_FLAG : RIGHT_SUBTREE_FLAG;

        preOrderPrint(sb, "", pointerForLeft, root.getLeft(), root.getRight() != null);
        preOrderPrint(sb, "", pointerForRight, root.getRight(), false);
        return sb.toString();
    }

    /**
     * 前序遍历
     * 子节点打印
     */
    private void preOrderPrint(StringBuilder sb, String padding, String pointer, BinaryTree<T>.Node<T> curr, boolean hasRightSibling) {
        if (curr == null) {
            return;
        }
        sb.append(padding).append(pointer);
        sb.append(curr.getValue()).append(NEWLINE_FLAG);
        StringBuilder paddingBuilder = new StringBuilder(padding);
        if (hasRightSibling) {
            paddingBuilder.append(PADDING_FLAG + "  ");
        } else {
            paddingBuilder.append("   ");
        }

        String paddingForBoth = paddingBuilder.toString();
        String pointerForRight = RIGHT_SUBTREE_FLAG;
        String pointerForLeft = (curr.getRight() != null) ? LEFT_SUBTREE_FLAG : RIGHT_SUBTREE_FLAG;


        preOrderPrint(sb, paddingForBoth, pointerForLeft, curr.getLeft(), curr.getRight() != null);
        preOrderPrint(sb, paddingForBoth, pointerForRight, curr.getRight(), false);
    }

    public AVLBinaryTreePrinter() {
    }

    public AVLBinaryTreePrinter(AVLBinaryTree tree) {
        this.tree = tree;
    }

    public AVLBinaryTree getTree() {
        return tree;
    }

    public void setTree(AVLBinaryTree tree) {
        this.tree = tree;
    }
}
