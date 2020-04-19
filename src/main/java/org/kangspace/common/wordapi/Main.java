package org.kangspace.common.wordapi;

/**
 * 2019/5/29 10:52
 *
 * @author kango2gler@gmail.com
 */
public class Main {
    final static String URL = "jdbc:oracle:thin:@192.168.10.27:1521:ettdb";
    final static String USERNAME = "ett16111";
    final static String PASSWORD = "ett16111";
    //"d:\\tmp\\";
    final static String RESULT_FILE_PATH = null;
    final static String WORDS_API_KEY = "a3638397fbmshd069acde620b1a5p12e9bbjsn877f1da55151";

    //22001
    //TODO 需要调整ETTWordHandle.handle中的 List<Word> list = queryNoHandleWordListFromDB
    public static void main(String[] args) throws InterruptedException {
        ETTWordHandle ettWordHandle = new ETTWordHandle(URL,USERNAME,PASSWORD,RESULT_FILE_PATH );
        ettWordHandle.setWordApiKey(WORDS_API_KEY);
        ettWordHandle.setLimit(5000);
        ettWordHandle.handle();
    }
}
