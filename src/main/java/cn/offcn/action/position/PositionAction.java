package cn.offcn.action.position;

import cn.offcn.entity.Employee;
import cn.offcn.service.position.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author loloSun
 * @Date 2019/6/17 0017 15:14
 * @Version 1.0
 */
@Controller
@RequestMapping("/position")
public class PositionAction {

    @Autowired
    private PositionService positionService;

    @RequestMapping("/getEmpByPosition")
    @ResponseBody
    public List<Employee> getEmpByPosition(String position){
        System.out.println(position);
        return positionService.getEmpByPosition(position);
    }

}
