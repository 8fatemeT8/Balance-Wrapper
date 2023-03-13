package com.refah.walletwrapper.utils.log;

import ch.qos.logback.classic.db.DBAppender;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class CsiDBAppender extends DBAppender {

    final static Logger logger = LoggerFactory.getLogger("csi.event.log");

    protected String insertPropertiesSQL;
    protected String insertExceptionSQL;
    protected String insertSQL;
    protected static final Method GET_GENERATED_KEYS_METHOD;

    private CsiDBNameResolver dbNameResolver;
    static final int requestid_INDEX = 1;
    static final int uri_INDEX = 2;
    static final int uritype_INDEX = 3;
    static final int operation_INDEX = 4;
    static final int status_INDEX = 5;
    static final int servicetype_INDEX = 6;
    static final int parent_INDEX = 7;
    static final int EVENT_ID_INDEX = 8;

    static {
        // PreparedStatement.getGeneratedKeys() method was added in JDK 1.4
        Method getGeneratedKeysMethod;
        try {
            // the
            getGeneratedKeysMethod = PreparedStatement.class.getMethod(
                    "getGeneratedKeys", (Class[]) null);
        } catch (Exception ex) {
            getGeneratedKeysMethod = null;
        }
        GET_GENERATED_KEYS_METHOD = getGeneratedKeysMethod;
    }

    public CsiDBAppender() {
    }

    public void setCsiDbNameResolver(CsiDBNameResolver dbNameResolver) {
        this.dbNameResolver = dbNameResolver;
    }

    @Override
    public void start() {

        super.start();
        if (dbNameResolver == null)
            dbNameResolver = new CsiDBNameResolver();
        insertSQL = CsiSQLBuilder.buildInsertSQL(dbNameResolver);

    }

    @Override
    protected void subAppend(ILoggingEvent event, Connection connection,
                             PreparedStatement insertStatement) throws Throwable {
        bindLoggingEventWithInsertStatement(insertStatement, event);
        System.out.println("===      INSERT STATEMENT      ===    " + insertStatement);

        int updateCount = -1;
        try {
            updateCount = insertStatement.executeUpdate();
        } catch (Exception e) {
            logger.error(" ERROR   ");
            e.printStackTrace();
        }

        System.out.println(" updateCount = " + updateCount);

        if (updateCount != 1) {
            logger.error(" ERROR  IT IS ");
            addWarn("Failed to insert loggingEvent");
        }
    }

    protected void secondarySubAppend(ILoggingEvent event,
                                      Connection connection, long eventId) throws Throwable {
    }

    private void bindLoggingEventWithInsertStatement(PreparedStatement stmt,
                                                     ILoggingEvent event) throws SQLException {
        String logdata = event.getFormattedMessage();
        logger.debug(" logdata   = " + logdata);
        parseLogData(logdata, stmt);
    }


    private void parseLogData(String logdata, PreparedStatement stmt) throws SQLException {

        String transactionId = null;
        String uri = null;
        String uriType = null;
        String tenant = null;
        String country = null;
        String action = null;
        String operation = null;
        String statusCode = null;
        String responseCode = null;
        String responseDescription = null;
        String faultCode = null;
        String faultDesc = null;
        String remarks = null;

        try {
            String[] entries = logdata.split(",");
            if (null != entries && entries.length > 0) {
                transactionId = entries[0];
                uri = entries[1];
                if (null != uri && !"".equalsIgnoreCase(uri)) {
                    uriType = getUriType(uri);
                }
                action = entries[4];
                operation = entries[5];
                statusCode = entries[7];
                responseCode = entries[8];
                responseDescription = entries[9];
                faultCode = entries[10];
                faultDesc = entries[11];
                remarks = entries[12];
            }

            stmt.setString(requestid_INDEX, transactionId);
            stmt.setString(uri_INDEX, uri);
            stmt.setString(uritype_INDEX, uriType);
            stmt.setString(operation_INDEX, operation);
            stmt.setString(status_INDEX, statusCode);
            stmt.setString(servicetype_INDEX, "data");
            stmt.setString(parent_INDEX, action);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            transactionId = null;
            uri = null;
            uriType = null;
            tenant = null;
            country = null;
            action = null;
            operation = null;
            statusCode = null;
            responseCode = null;
            responseDescription = null;
            faultCode = null;
            faultDesc = null;
            remarks = null;
        }

    }

    private String getUriType(String uri) {
        if (uri.contains("msisdn"))
            return "msisdn";
        if (uri.contains("iccid"))
            return "iccid";
        return null;
    }

    private java.sql.Date getSqlDate() {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSX");
        String nowDateStr = sdf.format(now);
        System.out.println("nowDateStr = " + nowDateStr);

        java.sql.Date toDB = null;
        try {
            toDB = new java.sql.Date(sdf.parse(nowDateStr).getTime());
            System.out.println("toDB = " + toDB);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return toDB;
    }

    @Override
    protected Method getGeneratedKeysMethod() {
        return GET_GENERATED_KEYS_METHOD;
    }

    @Override
    protected String getInsertSQL() {
        return insertSQL;
    }

    protected void insertProperties(Map<String, String> mergedMap,
                                    Connection connection, long eventId) throws SQLException {
    }
}
