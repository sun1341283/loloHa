package cn.offcn.service.function.impl;

import cn.offcn.entity.Function;
import cn.offcn.mapper.FunctionMapper;
import cn.offcn.service.function.FunctionService;
import cn.offcn.utils.OAResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FunctionServiceImpl implements FunctionService {

    @Autowired
    private FunctionMapper functionMapper;
    @Override
    public OAResult saveFunction(Function function){
       int rows=functionMapper.insert(function);
       if(rows==1){
           return OAResult.ok(200,"功能保存成功");
       }
        return OAResult.ok(400,"功能保存失败");
    }
    @Override
    public List<Function> getFunctionByTask(int mid){
        return functionMapper.getFunctionByTask(mid);
    }
}
