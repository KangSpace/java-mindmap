package org.kangspace.common.util.db.oracle;


import lombok.extern.slf4j.Slf4j;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class OracleConnRead extends OracleConn {

    public OracleConnRead() {
    }

    public OracleConnRead(boolean flagPool, boolean flagMovableResult) throws Exception {
        if(flagPool) {
            this.init("pool");
        } else {
            this.init("thin");
        }

        this.connect(flagMovableResult);
    }

    public OracleConnRead(boolean flagPool, boolean flagMovableResult, String linkFileName) throws Exception {
        if(flagPool) {
            this.init("pool", linkFileName);
        } else {
            this.init("thin", linkFileName);
        }

        this.connect(flagMovableResult);
    }

    public void init(String link) throws SQLException {
        this.init(link, this.dblink);
    }

    public void init(String link, String linkFileName) throws SQLException {
    }

    public void showProperty() {
    }

    public void connect() throws SQLException, ClassNotFoundException {
        try {
            InitialContext e = new InitialContext();
            Context envCtx = (Context)e.lookup("java:/comp/env");
            DataSource ds = (DataSource)envCtx.lookup("jdbc/ettslaves");
            this.conn = ds.getConnection();
            this.conn.setReadOnly(true);
            this.stmt = this.conn.createStatement(1004, 1007);
        } catch (Exception var4) {
            log.error(var4.getMessage(),var4);
        }

    }

    public void connect(boolean flagMovableResult) throws SQLException, ClassNotFoundException {
        if(flagMovableResult) {
            this.connect();
        } else {
            try {
                InitialContext e = new InitialContext();
                Context envCtx = (Context)e.lookup("java:/comp/env");
                DataSource ds = (DataSource)envCtx.lookup("jdbc/ettslaves");
                this.conn = ds.getConnection();
                this.conn.setReadOnly(true);
                this.stmt = this.conn.createStatement(1004, 1007);
            } catch (Exception var5) {
                log.error(var5.getMessage(),var5);
            }
        }

    }

    public void connect(String jdbcJndiName) throws SQLException, ClassNotFoundException {
        try {
            InitialContext e = new InitialContext();
            Context envCtx = (Context)e.lookup("java:/comp/env");
            DataSource ds = (DataSource)envCtx.lookup(jdbcJndiName);
            this.conn = ds.getConnection();
            this.conn.setReadOnly(true);
            this.stmt = this.conn.createStatement(1004, 1007);
        } catch (Exception var5) {
            log.error(var5.getMessage(),var5);
        }

    }

    public void commit() throws SQLException {
    }

    public void setAutoCommit(boolean autoCommit) throws SQLException {
    }

    public void rollback() throws SQLException {
    }

    public ResultSet getResultSet(String sql) throws SQLException {
        log.info(sql);
        String sqlUpper = sql.toUpperCase();
        if(sqlUpper.indexOf("INSERT") < 0 && sqlUpper.indexOf("UPDATE") < 0 && sqlUpper.indexOf("DELETE") < 0) {
            return this.stmt.executeQuery(sql);
        } else {
            throw new SQLException("not support insert/update/delete");
        }
    }

    public boolean execute(String sql) throws SQLException {
        log.info(sql);
        String sqlUpper = sql.toUpperCase();
        if(sqlUpper.indexOf("INSERT") < 0 && sqlUpper.indexOf("UPDATE") < 0 && sqlUpper.indexOf("DELETE") < 0) {
            return this.stmt.execute(sql);
        } else {
            throw new SQLException("not support insert/update/delete");
        }
    }

    public int update(String sql) throws SQLException {
        log.info(sql);
        String sqlUpper = sql.toUpperCase();
        if(sqlUpper.indexOf("INSERT") < 0 && sqlUpper.indexOf("UPDATE") < 0 && sqlUpper.indexOf("DELETE") < 0) {
            return this.stmt.executeUpdate(sql);
        } else {
            throw new SQLException("not support insert/update/delete");
        }
    }

    public void close() throws SQLException {
        try {
            if(this.stmt != null) {
                this.stmt.close();
            }
        } catch (Exception var3) {
            log.error(var3.getMessage(), var3);
        }

        try {
            if(this.conn != null) {
                this.conn.close();
            }
        } catch (Exception var2) {
            log.error(var2.getMessage(),var2);
        }

    }
}