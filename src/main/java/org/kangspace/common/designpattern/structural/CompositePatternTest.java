package org.kangspace.common.designpattern.structural;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合模式
 * 将对象组合成树形结构以表示"部分-整体"的层次结构。 使得用户对单个对象和组合对象的使用具有一致性
 * 样例: 目录层级
 * 2020/1/6 18:05
 *
 * @author kango2gler@gmail.com
 */
public class CompositePatternTest {
    /**
     * 抽象节点
     */
    static abstract class Node {
        private String name;
        private String type;

        abstract void add(Node node);

        abstract void removed(Node node);

        abstract void foreach();

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void printDesc() {
            System.out.println(getType() + ": " + this.getName());
            if (this instanceof Folder) {
                ((Folder) this).subNodes.forEach(t -> {t.printDesc();});
            }
        }
    }

    /**
     * 文件
     */
    static class File extends Node {
        public File() {
            setType("File");
        }
        public File(String name) {
            this();
            setName(name);
        }

        @Override
        void add(Node node) {
        }

        @Override
        void removed(Node node) {
        }

        @Override
        void foreach() {
            System.out.println("File: " + getName());
        }
    }

    /**
     * 目录
     */
    static class Folder extends Node {
        List<Node> subNodes = new ArrayList<>();

        public Folder() {
            setType("Folder");
        }
        public Folder(String name) {
            this();
            setName(name);
        }

        @Override
        void add(Node node) {
            this.subNodes.add(node);
        }

        @Override
        void removed(Node node) {
            this.subNodes.remove(node);
        }

        @Override
        void foreach() {
            subNodes.forEach(node -> {
                printDesc();
            });

        }
    }

    static void main() {
        Node baseFolder = new Folder("baseFolder");
        Node file1 = new File("- File1");
        Node file2 = new File("- File2");
        Node file3 = new File("- File3");
        Node secondFolder = new Folder("- secondFolder");
        Node file21 = new File("-- File1-2");
        Node file22 = new File("-- File2-2");
        Node file23 = new File("-- File3-2");
        Node thirdFolder = new Folder("-- thirdFolder");
        Node file31 = new File("--- File1-2");
        Node file32 = new File("--- File2-2");
        Node file33 = new File("--- File3-2");

        baseFolder.add(file1);
        baseFolder.add(file2);
        baseFolder.add(file3);
        baseFolder.add(secondFolder);

        secondFolder.add(file21);
        secondFolder.add(file22);
        secondFolder.add(file23);
        secondFolder.add(thirdFolder);

        thirdFolder.add(file31);
        thirdFolder.add(file32);
        thirdFolder.add(file33);

        baseFolder.printDesc();

    }

    public static void main(String[] args) {
        main();
    }
}
