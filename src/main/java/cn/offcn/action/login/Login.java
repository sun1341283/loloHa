package cn.offcn.action.login;

import cn.offcn.entity.Employee;
import cn.offcn.service.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author loloSun
 * @Date 2019/6/19 0019 9:51
 * @Version 1.0
 */
@Controller
public class Login {
    @Autowired
    private LoginService loginService;
    @RequestMapping("/Login")
    public String login(@RequestParam("username")String username, @RequestParam("password")String password, HttpServletRequest request){
        Employee employee = loginService.login(username,password);
        if (employee != null){
            request.getSession().setAttribute("user",employee);
            return "main/index";
        }
        return "main/login";
    }
}
