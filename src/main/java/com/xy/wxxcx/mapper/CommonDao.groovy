package com.xy.wxxcx.mapper

import com.xy.wxxcx.mapper.sqlProvider.CommonSQLProvider
import org.apache.ibatis.annotations.InsertProvider
import org.apache.ibatis.annotations.SelectProvider
import org.apache.ibatis.annotations.UpdateProvider
import org.springframework.stereotype.Component

/**
 * @author xy
 */
@Component
interface CommonDao {
    @SelectProvider(type = CommonSQLProvider.class,method ="commonSelect")
    List<Map<String,Object>> select(String tableName,Map<String,Object>params)
    @InsertProvider(type = CommonSQLProvider.class,method ="commonInsert")
    int insert(String tableName,Map<String,Object>params)
    @UpdateProvider(type = CommonSQLProvider.class,method ="commonUpdate")
    int update(String tableName,Map<String, Object> setParams,Map<String, Object> whereParams)
}
