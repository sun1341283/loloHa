package cn.offcn.action.employee;

import cn.offcn.entity.Employee;
import cn.offcn.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/emp")
public class EmployeeAction {

    @Autowired
    private EmployeeService employeeService;

    @ResponseBody
    @RequestMapping("/postion/emps")
    public List<Employee> getEmployeeByPostitionName(String postitonName){
        return employeeService.getEmployeeBycPostionName(postitonName);
    }

    @ResponseBody
    @RequestMapping("/Programmer/emps")
    public List<Employee> getEmployeesLikePostionName(String postionName){

        return employeeService.getEmployeesLikePostionName(postionName);
    }

    @ResponseBody
    @RequestMapping("/showReceiveEmp")
    public List<Employee> showReceiveEmp(HttpSession session){

        Employee employee=(Employee)session.getAttribute("user");
        return  employeeService.getReceiveEmp(employee.getEid());
    }
}
