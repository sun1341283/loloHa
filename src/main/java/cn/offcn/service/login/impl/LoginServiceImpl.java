package cn.offcn.service.login.impl;

import cn.offcn.entity.Employee;
import cn.offcn.entity.EmployeeExample;
import cn.offcn.mapper.EmployeeMapper;
import cn.offcn.service.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author loloSun
 * @Date 2019/6/19 0019 15:34
 * @Version 1.0
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Override
    public Employee login(String username, String password) {
        final EmployeeExample employeeExample = new EmployeeExample();
        final EmployeeExample.Criteria criteria = employeeExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        criteria.andPasswordEqualTo(password);
        final List<Employee> employees = employeeMapper.selectByExample(employeeExample);
        if (employees != null && employees.size() > 0){
            return employees.get(0);
        }
        return null;
    }
}
