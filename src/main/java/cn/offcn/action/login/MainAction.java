package cn.offcn.action.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mains")
public class MainAction {

      @RequestMapping("/{page}")
      public String  forwardMainPage(@PathVariable("page") String page){
           return "main/"+page;
      }
}
