package com.xy.wxxcx.mapper.sqlProvider;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * @author xy
 */
public class CommonSQLProvider {
    public String commonSelect(String tableName, Map<String, Object> params) {
        return new SQL() {
            {
                SELECT("*");
                FROM(tableName);
                if (params != null) {
                    params.forEach((k, v) -> WHERE(k + "= '" + v+"'"));
                }
            }
        }.toString();
    }

    public String commonInsert(String tableName, Map<String, Object> params) {
        return new SQL() {
            {
                INSERT_INTO(tableName);
                params.forEach((k, v) -> VALUES(k, "'"+v+"'"));
            }
        }.toString();
    }
}
