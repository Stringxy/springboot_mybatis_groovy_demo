package com.xy.wxxcx.mapper;

import com.xy.wxxcx.entity.Question;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author xy
 * 2018/1/8
 */
@Component
public interface QuestionDao {
    @Select("SELECT * FROM t_question WHERE exam_id = #{examId} AND number IN (${numbers})")
    List<Question> fetch(@Param("examId")int examId, @Param("numbers")String numbers);
}
