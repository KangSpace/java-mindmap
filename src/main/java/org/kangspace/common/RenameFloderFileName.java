package org.kangspace.common;

import java.io.File;

/**
 * 重命名文件夹下所有文件名
 * 2018/6/12 13:36
 *
 * @author kango2gler@gmail.com
 */
public class RenameFloderFileName {
    public static void rename(String path){
        File floder = new File(path);
        if(floder.isDirectory()){
            File[] files = floder.listFiles();
            for (File file : files) {
                String fileName = file.getAbsolutePath()+".jpg";
                File temp = new File(fileName);
                file.renameTo(temp);
            }
        }
    }

    public static void main(String[] args) {
        String path = "C:\\Users\\kangxuefeng\\Desktop\\Assets";
        rename(path);
        System.out.println("OK");
    }
}
