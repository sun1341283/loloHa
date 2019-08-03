package cn.offcn.action.task;

import cn.offcn.entity.Employee;
import cn.offcn.entity.Task;
import cn.offcn.service.task.TaskService;
import cn.offcn.utils.OAResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/task")
public class TaskAction {

    @Autowired
    private TaskService taskService;

    @RequestMapping("/forward/{page}")
    public String forwardTaskPage(@PathVariable("page") String page){

         return "task/"+page;
    }

    @ResponseBody
    @RequestMapping("/add")
    public OAResult addTask(Task task, HttpServletRequest request){
        Employee employee=(Employee)request.getSession().getAttribute("user");
        //设置分配者
        task.setEmpFk(employee.getEid());
        return taskService.saveTask(task);
    }

    @RequestMapping("/employee/tasks/{page}")
    public String getTaskByEmployee(@PathVariable("page") String page, HttpServletRequest request){
        Employee employee=(Employee)request.getSession().getAttribute("user");
        List<Task> taskList=taskService.getTaskByEmployee(employee.getEid());
        request.setAttribute("taskList",taskList);
        return "task/"+page;
    }

    @ResponseBody
    @RequestMapping("/status/update")
    public OAResult updateTaskStatus(Integer taskId,Integer status){

        return taskService.updateTaskStatus(taskId,status);
    }
}
