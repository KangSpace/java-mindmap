package org.kangspace.common.util.db.oracle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

public class CRUDTools {
    public CRUDTools() {
    }

    public static int insertDeleteUpdate(OracleConn orc, String sql) throws Exception {
        byte i = 0;
        if(orc.update(sql) > 0) {
            i = 1;
        }

        return i;
    }

    public static ResultSet getResultSet(OracleConn orc, String sql) throws Exception {
        ResultSet rs = null;
        rs = orc.getResultSet(sql);
        return rs;
    }

    public static int getResultNum(OracleConn orc, String sql) throws Exception {
        int num = 0;
        ResultSet rs = null;
        rs = orc.getResultSet(sql);
        if(rs != null && rs.next()) {
            num = rs.getInt(1);
        }

        return num;
    }

    private static void apply(PreparedStatement pstmt, List params) throws Exception {
        try {
            if(params != null & params.size() > 0) {
                Iterator ex = params.iterator();

                for(int index = 1; ex.hasNext(); ++index) {
                    Object obj = ex.next();
                    if(obj == null) {
                        pstmt.setObject(index, "");
                    } else {
                        pstmt.setObject(index, obj);
                    }
                }
            }

        } catch (SQLException var5) {
            throw new Exception("can not apply parameter", var5);
        }
    }

    private static List convert(ResultSet rs) throws Exception {
        ArrayList retList = new ArrayList();

        try {
            ResultSetMetaData ex = rs.getMetaData();
            int colCount = ex.getColumnCount();

            while(rs.next()) {
                HashMap recordMap = new HashMap();

                for(int i = 1; i <= colCount; ++i) {
                    String name = ex.getColumnName(i);
                    Object value = rs.getObject(i);
                    recordMap.put(name, value);
                }

                retList.add(recordMap);
            }

            return retList;
        } catch (SQLException var8) {
            throw new Exception("can not convert resultset to list of map", var8);
        }
    }

    public static List query(OracleConn orc, String sql, List params) throws Exception {
        List result = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = orc.getConnection().prepareStatement(sql);
            apply(pstmt, params);
            rs = pstmt.executeQuery();
            result = convert(rs);
        } catch (SQLException var17) {
            throw new Exception("can not execute query", var17);
        } finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException var16) {
                    ;
                }
            }

            if(pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException var15) {
                    ;
                }
            }

        }

        return result;
    }

    public static ResultSet queryResults(OracleConn orc, String sql, List params) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = orc.getConnection().prepareStatement(sql);
            apply(pstmt, params);
            rs = pstmt.executeQuery();
            return rs;
        } catch (SQLException var6) {
            throw new Exception("can not execute query", var6);
        }
    }

    public static Object queryOne(OracleConn orc, String sql, List params) throws Exception {
        List list = query(orc, sql, params);
        System.out.println("ssssssssssssssssssssssselect" + sql);
        if(list != null && list.size() != 0) {
            int index1 = list.size() - 1;
            Map record = (Map)list.get(index1);
            if(record != null && record.size() != 0) {
                return record.values().toArray()[0];
            } else {
                throw new Exception("data not exist");
            }
        } else {
            throw new Exception("data not exist");
        }
    }

    public static int execute(OracleConn orc, String sql, List params) throws Exception {
        boolean ret = false;
        PreparedStatement pstmt = null;

        int ret1;
        try {
            pstmt = orc.getConnection().prepareStatement(sql);
            apply(pstmt, params);
            ret1 = pstmt.executeUpdate();
        } catch (SQLException var13) {
            throw new Exception("", var13);
        } finally {
            if(pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException var12) {
                    ;
                }
            }

        }

        return ret1;
    }

    public static List[] queryBatch(OracleConn orc, String[] sqlArray, List[] paramArray) throws Exception {
        ArrayList rets = new ArrayList();
        if(sqlArray.length != paramArray.length) {
            throw new Exception("sql size not equal parameter size");
        } else {
            for(int i = 0; i < sqlArray.length; ++i) {
                String sql = sqlArray[i];
                List param = paramArray[i];
                List ret = query(orc, sql, param);
                rets.add(ret);
            }

            return (List[])rets.toArray();
        }
    }

    public static int[] executeBatch(OracleConn orc, String[] sqlArray, List[] paramArray) throws Exception {
        ArrayList rets = new ArrayList();
        if(sqlArray.length != paramArray.length) {
            throw new Exception("sql size not equal parameter size");
        } else {
            int i;
            for(int retArray = 0; retArray < sqlArray.length; ++retArray) {
                i = execute(orc, sqlArray[retArray], paramArray[retArray]);
                rets.add(new Integer(i));
            }

            int[] var6 = new int[rets.size()];

            for(i = 0; i < var6.length; ++i) {
                var6[i] = ((Integer)rets.get(i)).intValue();
            }

            return var6;
        }
    }
}
