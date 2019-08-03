package cn.offcn.service.function;

import cn.offcn.entity.Function;
import cn.offcn.utils.OAResult;

import java.util.List;

public interface FunctionService {

    public OAResult saveFunction(Function function);

    public List<Function> getFunctionByTask(int mid);
}
