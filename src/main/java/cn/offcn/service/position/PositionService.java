package cn.offcn.service.position;

import cn.offcn.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author loloSun
 * @Date 2019/6/17 0017 15:16
 * @Version 1.0
 */
@Service
public interface PositionService {

    /**
     * 根据职位名称查出人名 需要用到职位表和员工表联合
     * @param position
     * @return
     */
    List<Employee> getEmpByPosition(String position);
}
