package org.kangspace.common.util.db.oracle;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;

public class OracleConn {
    public static final String ORACLE_DRIVER="oracle.jdbc.driver.OracleDriver";
    private Logger log = LoggerFactory.getLogger(this.getClass());
    protected Connection conn = null;
    protected Statement stmt = null;
    protected String dblink = "dblink20.properties";

    public OracleConn() {
    }

    public OracleConn(boolean flagPool, boolean flagMovableResult) throws Exception {
        if (flagPool) {
            this.init("pool");
        } else {
            this.init("thin");
        }

        this.connect(flagMovableResult);
    }

    public OracleConn(boolean flagPool, boolean flagMovableResult, String linkFileName) throws Exception {
        if (flagPool) {
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
            Context envCtx = (Context) e.lookup("java:/comp/env");
            DataSource ds = (DataSource) envCtx.lookup("jdbc/ett20");
            this.conn = ds.getConnection();
            this.stmt = this.conn.createStatement(1004, 1007);
            this.conn.setAutoCommit(false);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }

    public void connect(String url, String username, String password) throws SQLException, ClassNotFoundException {
        try {
            Class.forName(ORACLE_DRIVER);
            Connection connection = DriverManager.getConnection(url, username, password);
            this.conn = connection;
            this.stmt = this.conn.createStatement(1004, 1007);
            this.conn.setAutoCommit(false);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }

    public void connect(boolean flagMovableResult) throws SQLException, ClassNotFoundException {
        if (flagMovableResult) {
            this.connect();
        } else {
            try {
                InitialContext e = new InitialContext();
                Context envCtx = (Context) e.lookup("java:/comp/env");
                DataSource ds = (DataSource) envCtx.lookup("jdbc/ett20");
                this.conn = ds.getConnection();
                this.stmt = this.conn.createStatement(1004, 1007);
                this.conn.setAutoCommit(false);
            } catch (Exception var5) {
                var5.printStackTrace();
            }
        }

    }

    public void connect(String jdbcJndiName) throws SQLException, ClassNotFoundException {
        try {
            InitialContext e = new InitialContext();
            Context envCtx = (Context) e.lookup("java:/comp/env");
            DataSource ds = (DataSource) envCtx.lookup(jdbcJndiName);
            this.conn = ds.getConnection();
            this.stmt = this.conn.createStatement(1004, 1007);
            this.conn.setAutoCommit(false);
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }

    public Connection getConnection() throws SQLException {
        return this.conn;
    }

    public Statement getStatement() throws SQLException {
        return this.stmt;
    }

    public void commit() throws SQLException {
        this.conn.commit();
    }

    public void setAutoCommit(boolean autoCommit) throws SQLException {
        this.conn.setAutoCommit(autoCommit);
    }

    public void rollback() throws SQLException {
        this.conn.rollback();
    }

    public ResultSet getResultSet(String sql) throws SQLException {
        log.info(sql);
        return this.stmt.executeQuery(sql);
    }

    public boolean execute(String sql) throws SQLException {
        log.info(sql);
        return this.stmt.execute(sql);
    }

    public int update(String sql) throws SQLException {
        log.info(sql);
        return this.stmt.executeUpdate(sql);
    }

    public void close() throws SQLException {
        if (this.conn != null) {
            this.conn.commit();
        }

        if (this.stmt != null) {
            this.stmt.close();
        }

        if (this.conn != null) {
            this.conn.close();
        }

    }
}
