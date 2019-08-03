package cn.offcn.service.module.impl;

import cn.offcn.entity.Module;
import cn.offcn.entity.ModuleExample;
import cn.offcn.mapper.ModuleMapper;
import cn.offcn.service.module.ModuleService;
import cn.offcn.utils.OAResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    private ModuleMapper moduleMapper;
    @Override
    public OAResult saveModule(Module module){
        int rows=moduleMapper.insert(module);
        if(rows==1){
            return OAResult.ok(200,"模块添加成功");
        }
        return OAResult.ok(400,"模块添加失败");
    }
    @Override
    public List<Module> getModuleByAnalysisId(Integer analysisId){
        ModuleExample moduleExample=new ModuleExample();
        ModuleExample.Criteria criteria = moduleExample.createCriteria();
        criteria.andAnalysisFkEqualTo(analysisId);
        return  moduleMapper.selectByExample(moduleExample);
    }
}
