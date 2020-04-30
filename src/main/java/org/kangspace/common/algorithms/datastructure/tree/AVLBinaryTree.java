package org.kangspace.common.algorithms.datastructure.tree;

/**
 * 平衡二叉树
 * 2020/4/29 16:56
 *
 */
public class AVLBinaryTree {
    AVLNode root;
    private final int LEFT = 1;
    private final int RIGHT = -1;
    private final int MAX_LEFT = 2;
    private final int MAX_RIGHT = -2;


    /**
     * 不完美，待优化
     * @param value
     */
    public void insert(int value) {
        root = insertNode(null,root,value);
        //平衡树操作
        balanceTree(root);
//        putData(root, value);
    }

    /**
     * 平衡树操作
     * @param root
     */
    public void balanceTree(AVLNode root) {
        while (root != null) {
            int bf = calcNodeBalanceFactor(root);
            if (bf == MAX_LEFT) {
                fixAfterInsertion(root,LEFT);
            }else if (bf == MAX_RIGHT) {
                fixAfterInsertion(root,RIGHT);
            }
            root = root.parent;
        }
    }

    public void fixAfterInsertion(AVLNode node,int type) {
        if (type == LEFT) {
            AVLNode left = node.left;
            //右旋
            if (left.left != null) {
                rightRotation(node);
            }
            //左右旋
            else if(left.right != null ){
                leftRotation(node);
                rightRotation(node);
            }
        } else if (type == RIGHT) {
            AVLNode right = node.right;
            //左旋
            if (right.right != null) {
                leftRotation(root);
            }
            //右左旋
            else if(right.left != null ){
                rightRotation(root);
                leftRotation(root);
            }
        }
    }

    public AVLNode rightRotation(AVLNode node) {
        if(node != null) {
            AVLNode left = node.left;
            node.left = left.right;
            // 如果left的右节点存在，则需将该右节点的父节点指给node节点
            if(left.right != null) {
                left.right.parent = node;
            }
            left.parent = node.parent;
            if(node.parent == null) {
                this.root = left;
            }
            // 即node节点在它原父节点的右子树中
            else if(node.parent.right == node) {
                node.parent.right = left;
            }
            else if(node.parent.left == node) {
                node.parent.left = left;
            }

            left.right = node;
            node.parent = left;
            return left;
        }
        return null;
    }
    public AVLNode leftRotation(AVLNode node) {
        if(node != null) {
            AVLNode right = node.right;
            node.right = right.left;
            if(right.left != null) {
                right.left.parent = node;
            }
            right.parent = node.parent;
            if(node.parent == null) {
                this.root = right;
            }
            else if(node.parent.right == node) {
                node.parent.right = right;
            }
            else if(node.parent.left == node) {
                node.parent.left = right;
            }
            right.left = node;
            node.parent = right;
            return right;
        }
        return null;

    }

    /**
     * 计算平衡因子
     * @param node
     * @return
     */
    public int calcNodeBalanceFactor(AVLNode node) {
        if (node == null) {
            return 0;
        }
        return length(node.left) - length(node.right);
    }


    public int length(AVLNode root) {
        return root == null ?0: 1 +Math.max(length(root.left),length(root.right));
    }

    /**
     * 插入节点
     * @param curr
     * @param value
     * @return
     */
    public AVLNode insertNode(AVLNode parent, AVLNode curr, int value) {
        if (curr == null) {
            return new AVLNode(parent,value);
        }
        if (curr.value > value) {
            curr.left = insertNode(curr,curr.left, value);
        } else if (curr.value < value) {
            curr.right = insertNode(curr,curr.right, value);
        }
        return curr;
    }

    @Deprecated
    private boolean putData(AVLNode node, int data) {
        if(node == null) {
            node  = new AVLNode(null,data);
            root = node;
            return true;
        }
        int t;
        AVLNode p;
        AVLNode temp = node;
        do {
            p = temp;
            t = temp.value - data;
            if(t < 0) {
                temp = temp.getRight();
            }
            else if(t > 0) {
                temp = temp.getLeft();
            }
            else {
                return false;
            }
        } while(temp != null);

        if(t < 0) {
            p.setLeft(new AVLNode(p, data));
        }
        else if(t > 0) {
            p.setRight( new AVLNode(p, data));
        }
        balanceTree(p); //平衡二叉树的方法
        return true;
    }

    /**
     * 平衡二叉树节点
     */
    public class AVLNode implements Comparable<AVLNode>{
        /**
         * 值
         */
        int value;
        AVLNode parent;
        AVLNode left;
        AVLNode right;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public AVLNode getParent() {
            return parent;
        }

        public void setParent(AVLNode parent) {
            this.parent = parent;
        }

        public AVLNode getLeft() {
            return left;
        }

        public void setLeft(AVLNode left) {
            this.left = left;
        }

        public AVLNode getRight() {
            return right;
        }

        public void setRight(AVLNode right) {
            this.right = right;
        }

        public AVLNode() {
        }

        public AVLNode(AVLNode parent,int value) {
            this.parent = parent;
            this.value = value;
        }

        @Override
        public int compareTo(AVLNode o) {
            return getValue() - o.getValue();
        }

    }

    public AVLNode getRoot() {
        return root;
    }

    public void setRoot(AVLNode root) {
        this.root = root;
    }

}
