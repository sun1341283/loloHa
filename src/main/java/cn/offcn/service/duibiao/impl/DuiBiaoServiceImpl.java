package cn.offcn.service.duibiao.impl;

import cn.offcn.entity.Datacollect;
import cn.offcn.entity.DatacollectExample;
import cn.offcn.entity.Indexvalue;
import cn.offcn.mapper.DatacollectMapper;
import cn.offcn.mapper.IndexvalueMapper;
import cn.offcn.service.duibiao.DuiBiaoService;
import cn.offcn.utils.OAResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DuiBiaoServiceImpl implements DuiBiaoService {

    @Autowired
    private DatacollectMapper datacollectMapper;

    @Autowired
    private IndexvalueMapper indexvalueMapper;

    public List<Datacollect> getInfoGroup(){

        return  datacollectMapper.getInfoGroup();

    }

    public List<Datacollect> getInfoBydacname(String dacname){

        return datacollectMapper.getInfoBydacname(dacname);
    }

    public Datacollect getOneByCname(Datacollect datacollect) {
        return datacollectMapper.getOneByCname(datacollect);
    }

    public Datacollect geOneByDaid(Integer daid) {
        return datacollectMapper.selectByPrimaryKey(daid);
    }

    public OAResult saveIndexValueInfo(Indexvalue indexvalue){

       int rows= indexvalueMapper.insertSelective(indexvalue);
       if(rows==1){
           return OAResult.ok(200,"对标信息添加成功");
       }
        return OAResult.ok(200,"对标信息添加失败");
    }
}
