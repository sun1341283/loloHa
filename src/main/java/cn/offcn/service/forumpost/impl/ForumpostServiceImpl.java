package cn.offcn.service.forumpost.impl;

import cn.offcn.entity.*;
import cn.offcn.mapper.EmployeeMapper;
import cn.offcn.mapper.EvaluateMapper;
import cn.offcn.mapper.ForumpostMapper;
import cn.offcn.service.forumpost.ForumpostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ForumpostServiceImpl implements ForumpostService {

    @Autowired
    private ForumpostMapper forumpostMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private EvaluateMapper evaluateMapper;

    public void saveForum(Forumpost forumpost){

        forumpost.setStats(1);//正常帖子
        forumpost.setCreatetime(new Date());
        forumpostMapper.insert(forumpost);
    }

    public List<Forumpost> getCurrentEmployeeForums(int empfk3){

        ForumpostExample forumpostExample=new ForumpostExample();
        ForumpostExample.Criteria criteria = forumpostExample.createCriteria();
        criteria.andEmpfk3EqualTo(empfk3);
        return forumpostMapper.selectByExample(forumpostExample);
    }

    public List<Forumpost>  getAllEmployeeForumposts(){

       return  forumpostMapper.getForumpostsTop();
    }

    public Forumpost getForumEvaluateByuForumId(Integer forumid){
        //通过帖子id来查找该帖子
        Forumpost forumpost=forumpostMapper.selectByPrimaryKey(forumid);
        //查找帖子是由谁来发的
        Employee employee=employeeMapper.selectByPrimaryKey(forumpost.getEmpfk3());
        forumpost.setEmployee(employee);
        //找一级评论
        List<Evaluate> evaluateList = getEvalByForumIdAndEvaidFk(forumid,null);
        forumpost.setEvaluateList(evaluateList);

        return forumpost;
    }

    //找评论
    public  List<Evaluate> getEvalByForumIdAndEvaidFk(Integer forumid,Integer evaidFk){

        EvaluateExample evaluateExample=new EvaluateExample();
        EvaluateExample.Criteria criteria = evaluateExample.createCriteria();
        criteria.andForumfkEqualTo(forumid);
        if(evaidFk==null){
            criteria.andEvaidfkIsNull();
        }else{
            criteria.andEvaidfkEqualTo(evaidFk);
        }

        List<Evaluate> evaluateList= evaluateMapper.selectByExampleWithBLOBs(evaluateExample);
        for(Evaluate evaluate : evaluateList){
                //找评论人
               Employee employee= employeeMapper.selectByPrimaryKey(evaluate.getEmpfk());
               evaluate.setEmployee(employee);
               List<Evaluate> subEvaluateList= getEvalByForumIdAndEvaidFk(evaluate.getForumfk(),evaluate.getEvaid());
               evaluate.setEvaluateList(subEvaluateList);
        }
        return evaluateList;
    }


    public void saveForumEvaluate(Evaluate evaluate){

        evaluateMapper.insert(evaluate);
    }



}
