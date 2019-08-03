package cn.offcn.service.analysis.impl;

import cn.offcn.action.analysis.AnalysisAction;
import cn.offcn.entity.Analysis;
import cn.offcn.entity.AnalysisExample;
import cn.offcn.mapper.AnalysisMapper;
import cn.offcn.service.analysis.AnalysisService;
import cn.offcn.utils.OAResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author loloSun
 * @Date 2019/6/18 0018 10:56
 * @Version 1.0
 */
@Service
public class AnalysisServiceImpl implements AnalysisService {
    @Autowired
    private AnalysisMapper analysisMapper;
    @Override
    public OAResult saveAnalysis(Analysis analysis) {
        analysis.setAddtime(new Date());
        final int insert = analysisMapper.insert(analysis);
        if (insert == 1){
            return OAResult.ok(200,"需求信息添加成功");
        }else {
            return OAResult.ok(400,"需求信息添加失败");
        }
    }

    @Override
    public List<Analysis> queryAnalysis(Analysis analysis) {
        final AnalysisExample example = new AnalysisExample();
        return analysisMapper.selectByExample(example);
    }
    @Override
    public Analysis getAnalysisById(Integer id){
        return analysisMapper.selectByPrimaryKey(id);
    }
}
