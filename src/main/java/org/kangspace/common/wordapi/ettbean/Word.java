package org.kangspace.common.wordapi.ettbean;

import org.kangspace.common.util.db.oracle.OracleConn;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 2019/5/28 16:45
 *
 * @author kangxuefeng@etiantian.com
 */
public class Word {
    private Long id;
    private String word;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", word='" + word + '\'' +
                '}';
    }

    /**
     * @param startId 起始id
     * @param limit   数据条数
     * @return
     * @author kangxuefeng@etiantian.com
     * @date 2019/5/28 16:54
     */
    public static List<Word> query(OracleConn orc, int startId, int limit) throws SQLException {
        String sql = " select ID,WORD from word where id > " + startId +" order by id";
        if (limit > 0) {
            sql = "select * from (" + sql + " ) t where rownum <=" + limit;
        }
        List<Word> list = new ArrayList<Word>();
        ResultSet results =  orc.getResultSet(sql);
        Word word;
        while (results.next()){
            word = new Word();
            word.setId(results.getLong("ID"));
            word.setWord(results.getString("WORD"));
            list.add(word);
        }
        return list;
    }

    /**
     * 查询为处理的数据
     * @param startId 起始id
     * @param limit   数据条数
     * @return
     * @author kangxuefeng@etiantian.com
     * @date 2019/5/28 16:54
     */
    public static List<Word> queryNoHandle(OracleConn orc, int startId, int limit) throws SQLException {
        String sql = " select t.ID,t.WORD from word t left join TEMP_WORDS_API_DATA twad on t.id = twad.word_id where twad.word_id is null and t.id > " + startId +" order by t.id  ";
        if (limit > 0) {
            sql = "select * from (" + sql + " ) t where rownum <=" + limit;
        }
        List<Word> list = new ArrayList<Word>();
        ResultSet results =  orc.getResultSet(sql);
        Word word;
        while (results.next()){
            word = new Word();
            word.setId(results.getLong("ID"));
            word.setWord(results.getString("WORD"));
            list.add(word);
        }
        return list;
    }
}
