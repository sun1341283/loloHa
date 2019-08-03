package cn.offcn.service.employee;

import cn.offcn.entity.Employee;

import java.util.List;

public interface EmployeeService {

    public List<Employee> getEmployeeBycPostionName(String postionName);

    public List<Employee> getEmployeesLikePostionName(String postionName);

    List<Employee> getReceiveEmp(int eid);
}
