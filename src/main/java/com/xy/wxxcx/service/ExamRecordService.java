package com.xy.wxxcx.service;

import com.github.pagehelper.PageHelper;
import com.xy.wxxcx.common.util.JavaBeanUtil;
import com.xy.wxxcx.entity.ExamRecord;
import com.xy.wxxcx.mapper.CommonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xy
 * 2018/1/8
 */
@Component
public class ExamRecordService {
    @Autowired
    private CommonDao commonDao;
    public boolean insert(ExamRecord examRecord) throws IllegalAccessException {
        return commonDao.insert("t_exam_record", JavaBeanUtil.beanToMap(examRecord)) > 0;
    }
    public  List<Map<String,Object>> findByUserId(long userid,int pageNo,int pageSize){
        Map<String,Object> params=new HashMap<>(1);
        params.put("user_id",userid);
        PageHelper.startPage(pageNo,pageSize);
        PageHelper.orderBy("create_time desc");
        return commonDao.select("t_exam_record",params);
    }

}
