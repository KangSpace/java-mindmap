package org.kangspace.common.util.db.oracle;


import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * 2019/5/28 18:28
 *
 * @author kango2gler@gmail.com
 */
@Slf4j
public class DBUtil {
    private String url;
    private String username;
    private String password;

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

    public DBUtil() {
    }

    public DBUtil(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    /**
     * 获取ett20数据库链接
     * @param isRead 0: read , 1:wirte
     * @Author kango2gler@gmail.com
     * @Date 2017/4/10 14:16
     * @return
     */
    public OracleConn getOracleCon(boolean isRead){
        //数据库连接
        try  {
            OracleConn orc= isRead?new OracleConnRead():new OracleConn();
            orc.connect(url,username,password);
            return orc;
        }  catch(Exception e)  {
            log.error("网校数据库连接失败:"+e.getMessage(),e);
            throw new UtilException(e);
        }
    }

    /**
     * 获取ett20数据库链接
     * @Author kango2gler@gmail.com
     * @Date 2017/4/10 14:16
     * @return
     */
    public OracleConn getOracleConRead(){
        return getOracleCon(true);
    }

    /**
     * 获取ett20数据库链接
     * @Author kango2gler@gmail.com
     * @Date 2017/4/10 14:16
     * @return
     */
    public OracleConn getOracleCon(){
        return getOracleCon(false);
    }

    /**
     * 关闭数据库连接
     * @param oracleConn
     * @Author kango2gler@gmail.com
     * @Date 2017/4/10 14:48
     * @return
     */
    public void closeOracleCon(OracleConn oracleConn){
        try {
            if(oracleConn !=null) {
                oracleConn.close();
            }
        } catch (SQLException e) {}
    }

    /**
     * SQL执行包裹方法,执行SQL时需调用该方法<br>
     * 内部执行顺序:<br>
     * 该方法中获取数据连接<br>
     * run<br>
     * 该方法中关闭数据连接<br>
     * @param isRead 是否只读
     * @param wrapper
     * @Author kango2gler@gmail.com
     * @Date 2017/4/10 15:00
     * @return
     */
    public Object oracleConnWrapper(Boolean isRead,OracleConnInnerWrapper wrapper) {
        Object obj;
        OracleConn oracleConn= getOracleCon(isRead);
        try {
            if(wrapper == null) {
                return null;
            }
            wrapper.setOracleCon(oracleConn);
            obj = wrapper.run();
            oracleConn.commit();
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw new UtilException(e);
        }finally {
            closeOracleCon(oracleConn);
        }
        return obj;
    }
    /**
     * SQL执行包裹方法,执行SQL时需调用该方法<br>
     * 内部执行顺序:<br>
     * 该方法中获取数据连接<br>
     * run<br>
     * 该方法中关闭数据连接<br>
     * @param wrapper
     * @Author kango2gler@gmail.com
     * @Date 2017/4/10 15:00
     * @return
     */
    public Object oracleConnWrapper(OracleConnInnerWrapper wrapper) {
        return oracleConnWrapper(false,wrapper);
    }
    /**
     * SQL执行内部包裹类
     * @Author kango2gler@gmail.com
     * @Date 2017/4/10 14:57
     * @return
     */
    public abstract static class OracleConnInnerWrapper{
        private OracleConn oracleCon;

        public OracleConn getOracleCon() {
            return oracleCon;
        }

        public void setOracleCon(OracleConn oracleCon) {
            this.oracleCon = oracleCon;
        }
        public OracleConnInnerWrapper(){ }
        public OracleConnInnerWrapper(OracleConn oracleCon){
            this.oracleCon = oracleCon;
        }
        public abstract Object run() throws Exception;
    }

    /**
     * 异常类
     * @Author kango2gler@gmail.com
     * @Date 2017/4/10 15:10
     */
    public class UtilException extends RuntimeException{
        public UtilException(Exception e) {
            super(e);
        }
        public UtilException(String message) {
            super(message);
        }
    }

    public static String oracleSingleQuotationHandle(String str){
        return str.replaceAll("'","''");
    }
}
