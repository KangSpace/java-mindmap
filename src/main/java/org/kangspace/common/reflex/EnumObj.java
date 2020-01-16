package org.kangspace.common.reflex;

import java.io.*;

/**
 * 2019/12/19 10:36
 *
 * @author kangxuefeng@etiantian.com
 */
public enum  EnumObj implements Serializable {
    A(1);
    private int i;
    //只支持private 和friendly
    EnumObj(int i) {
        this.i = i;
    }
    private void readResolve(ObjectInputStream obj){
        System.out.println("readResolve");
    }
    private void readObject(ObjectInputStream obj){
        System.out.println("readResolve");
    }

    public static void main(String[] args) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream ou = new ObjectOutputStream(outputStream);
            ou.writeObject(EnumObj.A);
            outputStream.close();

            ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
            ObjectInputStream ins = new ObjectInputStream(inputStream);
            EnumObj enumObj= (EnumObj) ins.readObject();
            System.out.println(enumObj);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
