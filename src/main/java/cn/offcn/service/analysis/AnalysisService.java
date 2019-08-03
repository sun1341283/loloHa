package cn.offcn.service.analysis;

import cn.offcn.action.analysis.AnalysisAction;
import cn.offcn.entity.Analysis;
import cn.offcn.utils.OAResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author loloSun
 * @Date 2019/6/18 0018 10:55
 * @Version 1.0
 */
@Service
public interface AnalysisService {

    OAResult saveAnalysis(Analysis analysis);

    List<Analysis> queryAnalysis(Analysis analysis);

    Analysis getAnalysisById(Integer id);
}
