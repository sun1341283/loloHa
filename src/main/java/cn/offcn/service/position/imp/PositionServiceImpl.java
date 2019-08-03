package cn.offcn.service.position.imp;

import cn.offcn.entity.Employee;
import cn.offcn.entity.EmployeeExample;
import cn.offcn.entity.PositionExample;
import cn.offcn.mapper.PositionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author loloSun
 * @Date 2019/6/17 0017 15:23
 * @Version 1.0
 */
@Service
public class PositionServiceImpl implements cn.offcn.service.position.PositionService {
    @Autowired
    private PositionMapper positionMapper;

    @Override
    public List<Employee> getEmpByPosition(String position) {
        return positionMapper.getEmpByPosition(position);

    }
}
