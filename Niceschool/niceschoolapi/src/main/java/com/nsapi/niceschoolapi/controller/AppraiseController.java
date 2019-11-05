package com.nsapi.niceschoolapi.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nsapi.niceschoolapi.common.config.MySysUser;
import com.nsapi.niceschoolapi.common.realm.AuthRealm;
import com.nsapi.niceschoolapi.entity.EvTchVO;
import com.nsapi.niceschoolapi.entity.EvaluateListDB;
import com.nsapi.niceschoolapi.entity.EvaluateRecordDB;
import com.nsapi.niceschoolapi.entity.SelEvRecardVO;
import com.nsapi.niceschoolapi.service.AppraiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("appraise")
public class AppraiseController extends BaseController {
    @Autowired
    private AppraiseService appraiseService;

    /**
     * 进入查询教评教师页面
     */
    @RequestMapping("seltch")
    public String seltch() {
        return "view/appraise/seltch";
    }

    /**
     * 进入教评问题管理页面
     */
    @RequestMapping("evlistcontrol")
    public String evListControl() {
        return "view/appraise/evlistcontrol";
    }

    /**
     * 添加问题
     */
    @RequestMapping("addoneev")
    @ResponseBody
    public Object addOneEv(EvaluateListDB evaluateListDB){
        int r = appraiseService.addOneEv(evaluateListDB);
        if(r==1){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 删除问题
     * */
    @RequestMapping("deleteoneev")
    @ResponseBody
    public Object deleteOneEv(Integer elid){
        int r = appraiseService.deleteOneEv(elid);
        if(r==1){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 编辑问题
     */
    @RequestMapping("editoneev")
    @ResponseBody
    public Object editOneEv(EvaluateListDB evaluateListDB){
        int r = appraiseService.editOneEv(evaluateListDB);
        if(r==1){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 获取教评教师列表
     */
    @RequestMapping("findalltch")
    @ResponseBody
    public Object findalltch(SelEvRecardVO selEvRecardVO){
        selEvRecardVO.setStuid(MySysUser.loginName());
        List<SelEvRecardVO> listAll = appraiseService.findAllTch(selEvRecardVO);
        Map<String, Object> tchData = new HashMap<String, Object>();
        //这是layui要求返回的json数据格式
        tchData.put("code", 0);
        tchData.put("msg", "");
        tchData.put("data", listAll);
        return tchData;
    }

    /**
     * 获取教评题目列表
     */
    @RequestMapping("findalltm")
    @ResponseBody
    public Object findalltm(EvaluateListDB evaluateListDB, Integer page, Integer limit){
        PageHelper.startPage(page, limit);
        List<EvaluateListDB> listAll = appraiseService.findAllTM(evaluateListDB);
        PageInfo pageInfo = new PageInfo(listAll);
        Map<String, Object> tmData = new HashMap<String, Object>();
        //这是layui要求返回的json数据格式
        tmData.put("code", 0);
        tmData.put("msg", "");
        //将全部数据的条数作为count传给前台（一共多少条）
        tmData.put("count", pageInfo.getTotal());
        //将分页后的数据返回（每页要显示的数据）
        tmData.put("data", pageInfo.getList());
        return tmData;
    }

    /**
     * 查询教评状态
     * */
    @RequestMapping("selonestate")
    @ResponseBody
    public Object selOneState(EvaluateRecordDB evaluateRecordDB){
        int r = appraiseService.selOneState(evaluateRecordDB);
        if(r>0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 提交教评
     */
    @RequestMapping("addoneappraise")
    @ResponseBody
    public Object addOneAppraise(EvTchVO evTchVO){
        int res1 = appraiseService.addOneAppraise(evTchVO);
        int res2 = appraiseService.updOneTchExam(evTchVO);
        if(res1==1&&res2==1){
            return true;
        }else{
            return false;
        }
    }

}
