package org.kangspace.common.wordapi;

import org.apache.commons.lang.StringEscapeUtils;
import org.kangspace.common.util.db.oracle.DBUtil;
import org.kangspace.common.wordapi.ettbean.TempWordsAPIData;
import org.kangspace.common.wordapi.ettbean.Word;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 2019/5/28 16:19
 *
 * @author kango2gler@gmail.com
 */
public class ETTWordHandle{
    private String url;
    private String username;
    private String password;
    /**
     * 结果保存文件路径
     */
    private String resultFilePath;
    private int limit;
    private String wordApiKey;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getResultFilePath() {
        return resultFilePath;
    }

    public void setResultFilePath(String resultFilePath) {
        this.resultFilePath = resultFilePath;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getWordApiKey() {
        return wordApiKey;
    }

    public void setWordApiKey(String wordApiKey) {
        this.wordApiKey = wordApiKey;
    }

    public ETTWordHandle() {
    }

    public ETTWordHandle(String url, String username, String password, String resultFilePath) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.resultFilePath = resultFilePath;
    }

    /**
     * 从数据库中获取单词列表
     * @param rowNum 起始行号
     * @param limit 每次查询条数
     * @author kango2gler@gmail.com
     * @date 2019/5/28 16:22
     * @return
     */
    public List<Word> queryWordListFromDB(final int rowNum, final Integer limit){
        return (List<Word>) new DBUtil(this.url,this.username,this.password).oracleConnWrapper(new DBUtil.OracleConnInnerWrapper() {
            @Override
            public Object run() throws Exception {
                return Word.query(this.getOracleCon(), rowNum,limit);
            }
        });
    }

   /**
     * 从数据库中获取未同步的单词列表
     * @param rowNum 起始行号
     * @param limit 每次查询条数
     * @author kango2gler@gmail.com
     * @date 2019/5/28 16:22
     * @return
     */
    public List<Word> queryNoHandleWordListFromDB(final int rowNum, final Integer limit){
        return (List<Word>) new DBUtil(this.url,this.username,this.password).oracleConnWrapper(new DBUtil.OracleConnInnerWrapper() {
            @Override
            public Object run() throws Exception {
                return Word.queryNoHandle(this.getOracleCon(), rowNum,limit);
            }
        });
    }

    /**
     * 将wordsapi result写入到数据库
     * @param result
     * @return
     */
    public int writeWordsAPIResultToDB(Long wordId,String result){
        if (result == null){
            throw new IllegalArgumentException("result must be not null!!!");
        }
        //音标(IPA)处理为 &#
        result = wordIpaToHtmlEntity(result);
        final TempWordsAPIData tempWordsAPIData = null;
        /*JSONObject json = JSONObject.fromObject(result);
        final TempWordsAPIData tempWordsAPIData = new TempWordsAPIData(wordId,
                json.has("word")?json.getString("word"):null,
                json.has("frequency")?new BigDecimal(json.getString("frequency")):null,
                json.has("pronunciation") ?
                        (json.get("pronunciation") instanceof JSONObject && json.getJSONObject("pronunciation").has("all")?
                                json.getJSONObject("pronunciation").getString("all"):json.getString("pronunciation"))
                        :null,
                return result
                );*/
        return  (Integer) new DBUtil(this.url,this.username,this.password).oracleConnWrapper(new DBUtil.OracleConnInnerWrapper() {
            @Override
            public Object run() throws Exception {
                return tempWordsAPIData.insertOrUpdate(this.getOracleCon());
            }
        });
    }

    public static Pattern IPA_PATTERN = Pattern.compile("[\u0250-\u02AF]");
    /**
     * 将音标转换为html 字符实体
     * @param str
     * @return
     */
    public static String wordIpaToHtmlEntity(String str){
        Matcher matcher = IPA_PATTERN.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()){
            String ipa = matcher.group();
            matcher.appendReplacement(sb, StringEscapeUtils.escapeHtml(ipa));
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 将wordsapi result写入到文件
     * @param word
     * @param result
     * @return
     */
    public int writeWordAPIResultToFile(String word,String result){
        if (resultFilePath != null && resultFilePath.trim().length()>0) {
            return 1;
        }
        return 0;
    }

    static final String ETTWORDHANDLE_POSITION_FILE = ".ETTWORDHANDLE_POSITION";
    /**
     * 将处理位置写入到文件
     * @param position 当前处理位置
     * @return
     */
    public int writeHandlePositionToFile(String position){
        String path = ETTWORDHANDLE_POSITION_FILE;
        if (resultFilePath != null && resultFilePath.trim().length()>0) {
            path = resultFilePath + path;
        }
        java.io.File file = new java.io.File(path);
        try(FileOutputStream out = new FileOutputStream(file)){
            out.write(position.getBytes());
            out.flush();
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
        return 1;
    }
    /**
     * 将处理位置写入到文件
     * @return
     */
    public Long readHandlePositionFromFile(){
        String path = ETTWORDHANDLE_POSITION_FILE;
        if (resultFilePath != null && resultFilePath.trim().length()>0) {
            path = resultFilePath + path;
        }
        java.io.File file = new java.io.File(path);
        try(FileInputStream in = new FileInputStream(file)){
            int i;
            StringBuffer sb = new StringBuffer();
            while((i=in.read())!=-1){
                sb.append((char)i);
            }
            if (sb.length()>0){
                return Long.valueOf(sb.toString());
            }
        }catch (Exception e){
            e.printStackTrace();
        };
        return 0L;
    }



    public void handle() throws InterruptedException {
        Long startTime = System.currentTimeMillis();
        //读取当前执行位置
        Long position = readHandlePositionFromFile();
        if (position == -1){
            System.out.println("Words Handle end!!!");
            return ;
        }
        int limit = this.limit>0?this.limit:10;

        //TODO 读取单词数据
        //List<Word> list = queryWordListFromDB(position.intValue(), limit);
        //读取未处理的单词
        List<Word> list = queryNoHandleWordListFromDB(position.intValue(), limit);
        if(list.isEmpty()){
            System.out.println("Words Handle end");
            return;
        }
        int subListSize = 4000;
        CountDownLatch countDownLatch = new CountDownLatch(list.size()%subListSize>0?(list.size()/subListSize)+1:(list.size()/subListSize));
        for(int i=0;i<list.size();i+=subListSize) {
            int to = i + subListSize;
            if(to>list.size()-1) {
                to = list.size();
            }
            List<Word> subList = list.subList(i, to);
            //此处使用新的ETTWordHandle对象
            new Thread(new ETTWordHandlThread(subList, countDownLatch,new ETTWordHandle(this.url,this.username,this.password,this.resultFilePath),this.wordApiKey)).start();
        }
        countDownLatch.await();
        position = -1L;
        //保存位置数据
        if(list.size()>0) {
            position = list.get(list.size() - 1).getId();
        }
        writeHandlePositionToFile(position+"");
        System.out.println("Words Handle finished,count:"+list.size());
        Long endTime = System.currentTimeMillis();
        System.out.println("cost time:"+(endTime-startTime)/1000 +" s");
    }


    class ETTWordHandlThread implements Runnable{
        private List<Word> list;
        private CountDownLatch countDownLatch;
        private ETTWordHandle ettWordHandle;
        private String WordsApiKey;

        public List<Word> getList() {
            return list;
        }

        public void setList(List<Word> list) {
            this.list = list;
        }

        public CountDownLatch getCountDownLatch() {
            return countDownLatch;
        }

        public void setCountDownLatch(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        public ETTWordHandle getEttWordHandle() {
            return ettWordHandle;
        }

        public void setEttWordHandle(ETTWordHandle ettWordHandle) {
            this.ettWordHandle = ettWordHandle;
        }

        public String getWordsApiKey() {
            return WordsApiKey;
        }

        public void setWordsApiKey(String wordsApiKey) {
            WordsApiKey = wordsApiKey;
        }

        public ETTWordHandlThread() {
        }

        public ETTWordHandlThread(List<Word> list) {
            this.list = list;
        }

        public ETTWordHandlThread(List<Word> list, CountDownLatch countDownLatch) {
            this.list = list;
            this.countDownLatch = countDownLatch;
        }

        public ETTWordHandlThread(List<Word> list, CountDownLatch countDownLatch, ETTWordHandle ettWordHandle) {
            this.list = list;
            this.countDownLatch = countDownLatch;
            this.ettWordHandle = ettWordHandle;
        }

        public ETTWordHandlThread(List<Word> list, CountDownLatch countDownLatch, ETTWordHandle ettWordHandle, String wordsApiKey) {
            this.list = list;
            this.countDownLatch = countDownLatch;
            this.ettWordHandle = ettWordHandle;
            WordsApiKey = wordsApiKey;
        }

        @Override
        public void run() {
            for (Word word: list){
                try {
                    String word_ = word.getWord();
                    word_ = URLEncoder.encode(word_, "UTF-8").replace("+","%20");
                    Long wordId = word.getId();
                    String resultWordAPIData = new WordAPIInterface(this.WordsApiKey).wordsRequest(word_);
                    //解析数据,保存数据到DB
                    int i = ettWordHandle.writeWordsAPIResultToDB(wordId, resultWordAPIData);
                    if (i<1 ){
                        throw new RuntimeException("save error: wordId:"+word_+",result:"+resultWordAPIData);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if (countDownLatch != null){
                countDownLatch.countDown();
            }
        }
    }
}
