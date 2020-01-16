package org.kangspace.common.wordapi.ettbean;

import org.kangspace.common.util.db.oracle.DBUtil;
import org.kangspace.common.util.db.oracle.OracleConn;
import oracle.sql.CLOB;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 2019/5/28 16:46
 *
 * @author kangxuefeng@etiantian.com
 */
public class TempWordsAPIData {
    private Long wordId;
    /**
     * 单词
     */
    private String word;
    /**
     * 频率
     */
    private BigDecimal frequency;
    /**
     * 音标
     */
    private String pronunciation;
    /**
     * 结果
     */
    private String resultJson;

    public Long getWordId() {
        return wordId;
    }

    public void setWordId(Long wordId) {
        this.wordId = wordId;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public BigDecimal getFrequency() {
        return frequency;
    }

    public void setFrequency(BigDecimal frequency) {
        this.frequency = frequency;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

    public String getResultJson() {
        return resultJson;
    }

    public void setResultJson(String resultJson) {
        this.resultJson = resultJson;
    }

    public TempWordsAPIData() {
    }

    @Override
    public String toString() {
        return "TempWordsAPIData{" +
                "wordId=" + wordId +
                ", word='" + word + '\'' +
                ", frequency=" + frequency +
                ", pronunciation='" + pronunciation + '\'' +
                ", resultJson='" + resultJson + '\'' +
                '}';
    }

    public TempWordsAPIData(Long wordId, String word, BigDecimal frequency, String pronunciation, String resultJson) {
        this.wordId = wordId;
        this.word = word;
        this.frequency = frequency;
        this.pronunciation = pronunciation;
        this.resultJson = resultJson;
    }

    public Word findOne(OracleConn orc) throws SQLException {
        if(this.word == null || this.word.trim().length()< 1){
            throw new IllegalArgumentException("word must be not null!!!");
        }
        String sql = "SELECT WORD_ID,WORD,FREQUENCY,PRONUNCIATION,RESULT_JSON FROM TEMP_WORDS_API_DATA WHERE WORD = '" + DBUtil.oracleSingleQuotationHandle(word )+ "'";
        ResultSet resultSet = orc.getResultSet(sql);
        Word word = null;
        if(resultSet.next()){
            word = new Word();
            word.setId(resultSet.getLong("WORD_ID"));
            word.setWord(resultSet.getString("WORD"));
        }
        return word;
    }

    public int update(OracleConn orc) throws SQLException {
        String sql = "UPDATE TEMP_WORDS_API_DATA SET ";
        String setStrs = "";
        if(this.wordId != null){
            setStrs += " WORD_ID = " + this.wordId;
        }
        if(this.frequency != null){
            if(setStrs.length()>0){
                setStrs += ",";
            }
           setStrs += " FREQUENCY = " + this.frequency;
        }
        if(this.pronunciation != null){
            if(setStrs.length()>0){
                setStrs += ",";
            }
           setStrs += " PRONUNCIATION = '" + this.pronunciation.replace("'","''")+"'";
        }
        String where =  " WHERE WORD = '"+ DBUtil.oracleSingleQuotationHandle(this.word ) +"'";
        int i = 0;
        if (setStrs.length() > 0){
            sql += setStrs + where;
            i = orc.update(sql);
        }
        if(this.resultJson != null){
            setStrs += " RESULT_JSON = " + this.resultJson;
            i = updateWordClob(orc, this.word, this.resultJson);
        }
        return i;
    }
    public int updateWordClob(OracleConn orc,String word,String clobVal){
        String updateSql = "UPDATE TEMP_WORDS_API_DATA SET RESULT_JSON = ? WHERE WORD = '"+DBUtil.oracleSingleQuotationHandle(this.word )+"'";
        try (PreparedStatement ps = orc.getConnection().prepareStatement(updateSql)){
            CLOB clob   = oracle.sql.CLOB.createTemporary(orc.getConnection(), false,oracle.sql.CLOB.DURATION_SESSION);
            clob.setString(1, clobVal);
            ps.setClob(1, clob);
            ps.executeUpdate();
            return 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    public int insert(OracleConn orc) throws SQLException {
        String sql = "INSERT INTO TEMP_WORDS_API_DATA(WORD_ID,WORD,FREQUENCY,PRONUNCIATION) VALUES(" +
                this.wordId + "," +
                (this.word != null ? "'" + this.word + "'" : null) + "," +
                this.frequency + "," +
                (this.pronunciation != null ? "'" + this.pronunciation.replace("'","''") + "'" : null) +
                ")";
        int i = orc.update(sql);
        if (i>0){
            //插入clob字段
            i = updateWordClob(orc, this.word, this.resultJson);
        }
        //RESULT_JSON
        //(this.resultJson != null ? "'" + this.resultJson.replace("'","''") + "'" : null) +
        System.out.println(sql);
        return i;
    }

    public int insertOrUpdate(OracleConn orc) throws SQLException {
        Word word = findOne(orc);
        if(word == null){
            return insert(orc);
        }
        return update(orc);
    }
}
