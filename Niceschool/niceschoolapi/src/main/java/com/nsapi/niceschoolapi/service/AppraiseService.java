package com.nsapi.niceschoolapi.service;

import com.nsapi.niceschoolapi.entity.EvTchVO;
import com.nsapi.niceschoolapi.entity.EvaluateListDB;
import com.nsapi.niceschoolapi.entity.EvaluateRecordDB;
import com.nsapi.niceschoolapi.entity.SelEvRecardVO;

import java.util.List;

public interface AppraiseService {
    //查询全部对应教评的老师
    List<SelEvRecardVO> findAllTch(SelEvRecardVO selEvRecardVO);
    //查询全部教评题目
    List<EvaluateListDB> findAllTM(EvaluateListDB evaluateListDB);
    //查询教评状态
    int selOneState(EvaluateRecordDB evaluateRecordDB);
    //添加教评记录
    int addOneAppraise(EvTchVO evTchVO);
    //更新教师分数及教评人数
    int updOneTchExam(EvTchVO evTchVO);
    //删除问题
    int deleteOneEv(Integer elid);
    //编辑问题
    int editOneEv(EvaluateListDB evaluateListDB);
    //添加问题
    int addOneEv(EvaluateListDB evaluateListDB);
}