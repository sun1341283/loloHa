package cn.offcn.action.analysis;

import cn.offcn.entity.Analysis;
import cn.offcn.service.analysis.AnalysisService;
import cn.offcn.utils.OAResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author loloSun
 * @Date 2019/6/18 0018 10:27
 * @Version 1.0
 */
@Controller
@RequestMapping("/analysis")
public class AnalysisAction {

    @Autowired
    private AnalysisService analysisService;
    @RequestMapping("forward/{page}")
    public String forward(@PathVariable("page") String page){
        return "analysis/"+page;
    }


    @RequestMapping("/saveAnalysis")
    @ResponseBody
    public OAResult saveAnalysis(Analysis analysis){
        return analysisService.saveAnalysis(analysis);
    }

    @RequestMapping("/queryAnalysis")
    @ResponseBody
    public List<Analysis> queryAnalysis(Analysis analysis){
        return analysisService.queryAnalysis(analysis);
    }

    @ResponseBody
    @RequestMapping("/proId/query")
    public Analysis getAnalysisById(Integer id){

        return analysisService.getAnalysisById(id);
    }
}
