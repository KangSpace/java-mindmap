package org.kangspace.common.wordapi;

import org.kangspace.common.http.MyHttpClient;

import java.util.HashMap;
import java.util.Map;

/**
 * WordApi接口
 * 2019/5/28 16:01
 *
 * @author kango2gler@gmail.com
 */
public class WordAPIInterface {
    public static final String WORDS_API_URL = "https://wordsapiv1.p.mashape.com/words/";
    public static final String HEADER_X_MASHAPE_KEY = "X-Mashape-Key";
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public WordAPIInterface() {
    }

    public WordAPIInterface(String key) {
        this.key = key;
    }

    public String wordsRequest(String word) {
        MyHttpClient client = new MyHttpClient();
        return client.get(WORDS_API_URL + word,getWordAPIRequestHeader());
    }

    public Map<String, String> getWordAPIRequestHeader() {
        Map<String, String> headerMap = new HashMap<String, String>();
        headerMap.put(HEADER_X_MASHAPE_KEY, this.key);
        return headerMap;
    }
}
