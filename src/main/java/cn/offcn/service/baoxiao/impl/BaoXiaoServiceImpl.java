package cn.offcn.service.baoxiao.impl;

import cn.offcn.entity.Baoxiao;
import cn.offcn.entity.BaoxiaoExample;
import cn.offcn.entity.Employee;
import cn.offcn.mapper.BaoxiaoMapper;
import cn.offcn.mapper.EmployeeMapper;
import cn.offcn.service.baoxiao.BaoXiaoService;
import cn.offcn.utils.OAResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BaoXiaoServiceImpl implements BaoXiaoService {

    @Autowired
    private BaoxiaoMapper baoxiaoMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public OAResult saveBaoXiao(Baoxiao baoxiao){

        //设置报销编号
        baoxiao.setBxid(UUID.randomUUID().toString());
        //设置报销的状态 1  未审批  2  已审批  3 驳回
        baoxiao.setBxstatus(1);
        int rows= baoxiaoMapper.insert(baoxiao);
        if(rows==1){
            return OAResult.ok(200,"报销单保存成功");
        }
        return OAResult.ok(400,"报销单保存失败");

    }
    @Override
    public List<Baoxiao> getMyBaoxiaoByEid(Integer eid){

        BaoxiaoExample baoxiaoExample=new BaoxiaoExample();
        BaoxiaoExample.Criteria criteria = baoxiaoExample.createCriteria();
        criteria.andEmpFkEqualTo(eid);
        return baoxiaoMapper.selectByExample(baoxiaoExample);
    }
    @Override
    public List<Baoxiao> getUndoBaoXiaos(){
        BaoxiaoExample baoxiaoExample=new BaoxiaoExample();
        BaoxiaoExample.Criteria criteria = baoxiaoExample.createCriteria();
        criteria.andBxstatusEqualTo(1);
        List<Baoxiao> baoxiaoList=baoxiaoMapper.selectByExample(baoxiaoExample);

        for(Baoxiao bx : baoxiaoList){
            Employee meployee=employeeMapper.selectByPrimaryKey(bx.getEmpFk());
            bx.setEmployee(meployee);
        }
        return baoxiaoList;
    }
    @Override
    public Baoxiao getOneBaoXiao(String bxid){
        Baoxiao baoxiao= baoxiaoMapper.selectByPrimaryKey(bxid);
        return baoxiao;
    }
    @Override
    public OAResult updateBaoxiaoByBxid(String bxid,Integer bxstatus,String pizhu,Integer empId){

        Baoxiao baoxiao=baoxiaoMapper.selectByPrimaryKey(bxid);
        baoxiao.setBxstatus(bxstatus);
        baoxiao.setResult(pizhu);
        baoxiao.setCaiwuFk(empId);

        int rows=baoxiaoMapper.updateByPrimaryKey(baoxiao);
        if(rows==1){
            return OAResult.ok(200,"审批成功");
        }
        return OAResult.ok(400,"审批成功");
    }
    @Override
    public OAResult updateBaoxiaoByBxid(Baoxiao baoxiao){

        Baoxiao bx=baoxiaoMapper.selectByPrimaryKey(baoxiao.getBxid());
        bx.setPaymode(baoxiao.getPaymode());
        bx.setTotalmoney(baoxiao.getTotalmoney());
        bx.setBxtime(baoxiao.getBxtime());
        bx.setBxremark(baoxiao.getBxremark());
        bx.setBxstatus(1);//未报销状态
        int rows= baoxiaoMapper.updateByPrimaryKey(bx);
        if(rows==1){
            return OAResult.ok(200,"报销单更改成功");
        }
        return OAResult.ok(400,"报销单更改失败");

    }
}
