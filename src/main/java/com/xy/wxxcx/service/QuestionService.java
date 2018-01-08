package com.xy.wxxcx.service;

import com.xy.wxxcx.entity.Question;
import com.xy.wxxcx.mapper.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author xy
 * 2018/1/8
 */
@Component
public class QuestionService {
    @Autowired
    private QuestionDao questionDao;
    private static Random random=new Random();
    public List<Question> fetchQuestions(int examId){
        List<Integer>numbers=new ArrayList<>();
        for(int i=0;i<10;i++){
            numbers.add(QuestionService.getRandom(numbers));
        }
        return questionDao.fetch(examId,listToStr(numbers));
    }

    private  static int getRandom(List<Integer> list){
        int number=random.nextInt(random.nextInt(74)+1);
        if(list.contains(random)){
            QuestionService.getRandom(list);
        }
        return number;
    }

    private String listToStr(List<Integer> list){
        StringBuffer stringBuffer=new StringBuffer();
        list.forEach(integer -> stringBuffer.append(integer+','));
        stringBuffer.deleteCharAt(stringBuffer.length()-1);
        return stringBuffer.toString();
    }
}
