package org.kangspace.common.algorithms.datastructure.stack;

/**
 * 栈实现的2种方式:
 * 1. 数组实现栈(Object[])
 * 2. 链表实现栈(单向链表,链尾为栈顶)
 * 2020/3/25 17:38
 *
 * @author kango2gler@gmail.com
 */
public class StackTest {
    /**
     * 栈接口
     */
    interface Stack {
        /**
         * 入栈
         *
         * @param obj
         */
        void push(Object obj);

        /**
         * 出站
         *
         * @return
         */
        Object pop();

        /**
         * 获取栈顶元素
         *
         * @return
         */
        Object top();

        /**
         * 是否空栈
         *
         * @return
         */
        boolean isEmpty();

        /**
         * 栈是否已满
         *
         * @return
         */
        boolean isFull();
    }

    /**
     * 数组实现栈
     */
    static class ArrayStack implements Stack {
        protected Integer[] stack;
        protected Integer maxsize;
        int topIdx;//栈顶下标

        public ArrayStack() {
            this(10);
        }

        public ArrayStack(Integer size) {
            maxsize = size;
            stack = new Integer[size];
            topIdx = 0;
        }

        /**
         * 入栈
         *
         * @param obj
         */
        @Override
        public void push(Object obj) {
            if (isFull()) {
                throw new StackOverflowError("栈已满！");
            }
            stack[(topIdx + 1 < maxsize ? topIdx++ : topIdx)] = (Integer) obj;
        }

        /**
         * 出站
         *
         * @return
         */
        @Override
        public Integer pop() {
            if (isEmpty()) {
                throw new StackOverflowError("栈空");
            }
            try {
                return stack[topIdx];
            } finally {
                stack[topIdx > 0 ? topIdx-- : topIdx] = null;
            }
        }

        /**
         * 获取栈顶元素
         *
         * @return
         */
        @Override
        public Integer top() {
            return stack[topIdx];
        }

        /**
         * 是否空栈
         *
         * @return
         */
        @Override
        public boolean isEmpty() {
            return topIdx == 0 && stack[topIdx] == null;
        }

        @Override
        public boolean isFull() {
            return maxsize < topIdx + 1;
        }
    }

    /**
     * 链表实现栈
     */
    static class NodeStack implements Stack {
        private Node top;
        private int size;
        private int maxsize;
        public NodeStack(){
            this(5);
        }

        public NodeStack(int initSize) {
            this.maxsize = initSize;
            size = 0;
        }
        @Override
        public void push(Object obj) {
            if(isFull()){
                throw new StackOverflowError("栈已满");
            }
            top = new Node((Integer) obj, top, null);
            size++;
        }

        @Override
        public Object pop() {
            if (isEmpty()) {
                throw new StackOverflowError("栈已空");
            }
            size --;
            Integer val = top.value;
            top = top.getBefore();
            return val;
        }

        @Override
        public Object top() {
            return top;
        }

        @Override
        public boolean isEmpty() {
            return size == 0 && top == null;
        }

        @Override
        public boolean isFull() {
            return size>= maxsize;
        }

        /**
         * 链表(单向链表)
         */
        class Node {
            private Integer value;
//            private Node next;
            private Node before;

            public Node() {
            }

            public Node(Integer value,Node before, Node next) {
                this.value = value;
                this.before = before;
//                this.next = next;
            }

            public Integer getValue() {
                return value;
            }

            public void setValue(Integer value) {
                this.value = value;
            }
/*
            public Node getNext() {
                return next;
            }

            public void setNext(Node next) {
                this.next = next;
            }
*/

            public Node getBefore() {
                return before;
            }

            public void setBefore(Node before) {
                this.before = before;
            }

            @Override
            public String toString() {
                return "Node{" +
                        "value=" + value +
//                        ", next=" + next +
                        ", before=" + before +
                        '}';
            }
        }
    }

    static void arrayStackTest(){
        Stack arrStack = new ArrayStack(5);
        arrStack.push(1);
        arrStack.push(2);
        arrStack.push(3);
        arrStack.push(4);
        arrStack.push(5);
        System.out.println(((ArrayStack) arrStack).pop());
        System.out.println(((ArrayStack) arrStack).pop());
        System.out.println(((ArrayStack) arrStack).pop());
        System.out.println(((ArrayStack) arrStack).pop());
        System.out.println(((ArrayStack) arrStack).pop());
        System.out.println(((ArrayStack) arrStack).pop());
    }

    static void nodeStackTest(){
        Stack nodeStack = new NodeStack(5);
        nodeStack.push(1);
        nodeStack.push(2);
        nodeStack.push(3);
        nodeStack.push(4);
        nodeStack.push(5);
//        nodeStack.push(6);
        System.out.println(((NodeStack) nodeStack).top());
        System.out.println(nodeStack.pop());
        System.out.println(nodeStack.pop());
        System.out.println(nodeStack.pop());
        System.out.println(nodeStack.pop());
        System.out.println(nodeStack.pop());
        System.out.println(nodeStack.pop());
    }

    public static void main(String[] args) {
//        arrayStackTest();
        nodeStackTest();
    }
}
