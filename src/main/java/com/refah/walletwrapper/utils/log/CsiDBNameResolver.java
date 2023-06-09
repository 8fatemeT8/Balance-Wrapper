package com.refah.walletwrapper.utils.log;

import ch.qos.logback.classic.db.names.DBNameResolver;

public class CsiDBNameResolver implements DBNameResolver {

    private String tableNamePrefix = "";

    public String getTableNamePrefix() {
        return tableNamePrefix;
    }

    public void setTableNamePrefix(String tableNamePrefix) {
        this.tableNamePrefix = tableNamePrefix;
    }

    @Override
    public <N extends Enum<?>> String getColumnName(N columnName) {
        return columnName.name().toLowerCase();
    }

    @Override
    public <N extends Enum<?>> String getTableName(N tableName) {
        if (tableName.name().toLowerCase().equals("transaction"))
            return tableNamePrefix + "transaction";
        throw new IllegalArgumentException(tableName + " is an unknown table name");
    }

}
