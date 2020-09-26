package org.kangspace.common.wordapi;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 2019/5/28 17:58
 *
 * @author kango2gler@gmail.com
 */
public class Test {
    final static String URL = "jdbc:oracle:thin:@localhost:1521:db";
    final static String USERNAME = "name";
    final static String PASSWORD = "pwd";
    //"d:\\tmp\\";
    final static String RESULT_FILE_PATH = null;
    public static void queryWordListTest(){
        System.out.println(new ETTWordHandle(URL,USERNAME,PASSWORD,RESULT_FILE_PATH ).queryWordListFromDB(0,10));
    }
    public static void insertTempWordAPIDataTest(){
        String result = "{\"word\":\"test\",\"a\":1}";
        System.out.println(new ETTWordHandle(URL,USERNAME,PASSWORD,RESULT_FILE_PATH ).writeWordsAPIResultToDB(447L,result));
    }

    public static void writeHandlePositionToFileTest(){
        System.out.println(new ETTWordHandle(URL,USERNAME,PASSWORD,RESULT_FILE_PATH).writeHandlePositionToFile("447"));
    }

    public static void readHandlePositionFromFileTest(){
        System.out.println(new ETTWordHandle(URL,USERNAME,PASSWORD,RESULT_FILE_PATH ).readHandlePositionFromFile());
    }

    public static void wordIpaToHtmlEntityTest(){
        String str = "'sər,veɪ";
        System.out.println(ETTWordHandle.wordIpaToHtmlEntity(str));
    }

    public static void urlEncoder() {
        try {
            System.out.println(URLEncoder.encode("  +","UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        queryWordListTest();
//        insertTempWordAPIDataTest();
//        writeHandlePositionToFileTest();
//        readHandlePositionFromFileTest();
//        wordIpaToHtmlEntityTest();
        urlEncoder();
//        Map<String, Object> map = new HashMap<>();
//        map.put("map", new HashMap<>());
//        map.put("list", new ArrayList<>());
//        JSONObject.fromObject(map).toString()
    }
}
