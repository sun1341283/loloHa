package cn.offcn.service.module;

import cn.offcn.entity.Module;
import cn.offcn.utils.OAResult;

import java.util.List;

public interface ModuleService {

    public OAResult saveModule(Module module);

    public List<Module> getModuleByAnalysisId(Integer analysisId);

}

