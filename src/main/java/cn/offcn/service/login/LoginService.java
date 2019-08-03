package cn.offcn.service.login;

import cn.offcn.entity.Employee;
import org.springframework.stereotype.Service;

/**
 * @Author loloSun
 * @Date 2019/6/19 0019 15:34
 * @Version 1.0
 */
@Service
public interface LoginService {
    Employee login(String username, String password);
}
