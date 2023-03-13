package com.refah.walletwrapper.utils.log;

public class CsiSQLBuilder {


    static String buildInsertSQL(CsiDBNameResolver dbNameResolver) {

        StringBuilder sqlBuilder = new StringBuilder("INSERT INTO iot_");
        sqlBuilder.append(dbNameResolver.getTableName(CsiTableName.TRANSACTION)).append(" (");
        sqlBuilder.append(dbNameResolver.getColumnName(CsiColumnName.uri)).append(", ");
        sqlBuilder.append(dbNameResolver.getColumnName(CsiColumnName.uritype)).append(", ");
        sqlBuilder.append(dbNameResolver.getColumnName(CsiColumnName.operation)).append(", ");
        sqlBuilder.append(dbNameResolver.getColumnName(CsiColumnName.status)).append(", ");
        sqlBuilder.append(dbNameResolver.getColumnName(CsiColumnName.servicetype)).append(", ");
        sqlBuilder.append(dbNameResolver.getColumnName(CsiColumnName.parent)).append(") ");

        sqlBuilder.append("VALUES (?, ?, ? ,?, ?, ?, ?)");
        return sqlBuilder.toString();
    }
}
