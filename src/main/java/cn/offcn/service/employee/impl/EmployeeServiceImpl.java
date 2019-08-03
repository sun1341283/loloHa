package cn.offcn.service.employee.impl;

import cn.offcn.entity.Employee;
import cn.offcn.entity.EmployeeExample;
import cn.offcn.mapper.EmployeeMapper;
import cn.offcn.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 根据职位查询员工
     * @param postionName
     * @return
     */
    @Override
    public List<Employee> getEmployeeBycPostionName(String postionName){
       return employeeMapper.getEmployeeBycPostionName(postionName);
    }
    @Override
    public List<Employee> getEmployeesLikePostionName(String postionName){

       return employeeMapper.getEmployeesLikePostionName("%"+postionName+"%");
    }

    @Override
    public List<Employee> getReceiveEmp(int eid){

        EmployeeExample employeeExample=new EmployeeExample();
        EmployeeExample.Criteria criteria = employeeExample.createCriteria();
        criteria.andEidNotEqualTo(eid);
        return  employeeMapper.selectByExample(employeeExample);
    }
}
