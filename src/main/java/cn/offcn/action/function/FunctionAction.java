package cn.offcn.action.function;

import cn.offcn.entity.Function;
import cn.offcn.service.function.FunctionService;
import cn.offcn.utils.OAResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/function")
public class FunctionAction {

     @Autowired
     private FunctionService functionService;

      @RequestMapping("/forward/{page}")
      public String forwardFunctionPage(@PathVariable("page") String page){
          return "project/"+page;
      }

      @ResponseBody
      @RequestMapping("/add")
      public OAResult addFunction(Function function){

          return functionService.saveFunction(function);
      }

      @ResponseBody
      @RequestMapping("/task/functions")
      public List<Function> getFunctionByTask(int mid){

          return functionService.getFunctionByTask(mid);
      }

}
