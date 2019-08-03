package cn.offcn.action.module;

import cn.offcn.entity.Module;
import cn.offcn.service.module.ModuleService;
import cn.offcn.utils.OAResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/module")
public class ModuleAction {

    @Autowired
    private ModuleService moduleService;

    @ResponseBody
    @RequestMapping("/add")
    public OAResult addModule(Module module){
        return moduleService.saveModule(module);
    }

    @ResponseBody
    @RequestMapping("/analysis/modules")
    public List<Module> getModuleByAnalysis(Integer analysisId){

        return moduleService.getModuleByAnalysisId(analysisId);
    }
}
