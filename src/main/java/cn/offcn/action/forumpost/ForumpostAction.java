package cn.offcn.action.forumpost;

import cn.offcn.entity.Employee;
import cn.offcn.entity.Evaluate;
import cn.offcn.entity.Forumpost;
import cn.offcn.service.forumpost.ForumpostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/forumpost")
public class ForumpostAction {

    @Autowired
    private ForumpostService forumpostService;

     @RequestMapping("/forward/{page}")
     public String showForumpostPage(@PathVariable("page") String page){
         return "forumpost/"+page;
     }

    @RequestMapping("/forwardEvaluate")
    public String forwardEvaluate(@RequestParam("forum")String forumid,HttpServletRequest request){
            request.setAttribute("forum",forumid);
        return "forumpost/forum-reply";
    }

     @RequestMapping("/add")
     public String addForum(Forumpost forumpost, HttpServletRequest request){

         //首先要获取登陆的用户
         Employee employee=(Employee)request.getSession().getAttribute("user");
         forumpost.setEmpfk3(employee.getEid());
         forumpostService.saveForum(forumpost);
         return "redirect:/forumpost/forward/show";
     }

     @ResponseBody
     @RequestMapping("/currentEmployee/forumposts")
     public List<Forumpost> currentEmployeeForumposts(HttpServletRequest request){
         Employee employee=(Employee)request.getSession().getAttribute("user");
         return forumpostService.getCurrentEmployeeForums(employee.getEid());
     }


     @ResponseBody
     @RequestMapping("/allEmployee/forumposts")
     public List<Forumpost>  showAllEmployeeForumposts(){

         return forumpostService.getAllEmployeeForumposts();
     }

     //@ResponseBody
     @RequestMapping("/forumId/forumEval")
     public String showForumEvaluateByuForumId(Integer forumid,Model model){

        Forumpost forumpost=forumpostService.getForumEvaluateByuForumId(forumid);
         model.addAttribute("forum",forumpost);
         return "forumpost/forum-reply";
     }

     @RequestMapping("/evaluate/add")
     public String addForumEvaluate(Evaluate evaluate,HttpServletRequest request){

         //保存评论
         Employee employee=(Employee)request.getSession().getAttribute("user");
         evaluate.setEmpfk(employee.getEid());
         evaluate.setUpdatetime(new Date());
         evaluate.setEvatime(new Date());
         forumpostService.saveForumEvaluate(evaluate);
         return "redirect:/forumpost/forumId/forumEval?forumid="+evaluate.getForumfk();
     }



}
