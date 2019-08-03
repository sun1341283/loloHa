package cn.offcn.action.project;

import cn.offcn.entity.Employee;
import cn.offcn.entity.Project;
import cn.offcn.service.project.ProjectService;
import cn.offcn.utils.OAResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author loloSun
 * @Date 2019/6/17 0017 9:13
 * @Version 1.0
 */
@Controller
@RequestMapping("/project")
public class ProjectAction {
    @Autowired
    private ProjectService projectService;

    @RequestMapping("/forward/{page}")
    public String forwardProjectPage(@PathVariable("page")String page){
        return "project/"+page;
    }

    @RequestMapping("/saveProject")
    @ResponseBody
    public OAResult saveProject(Project project){
        return projectService.saveProject(project);
    }

    @ResponseBody
    @RequestMapping("/query")
    public List<Project> query(){
        return projectService.query();
    }

    @ResponseBody
    @RequestMapping("/need/projects")
    public List<Project> getProjectsNeed(){

        return projectService.getProjectsNeed();
    }

    @ResponseBody
    @RequestMapping("/module/projects")
    public List<Project> getProjectByModule(){

        return projectService.getProjectByModule();
    }

    @ResponseBody
    @RequestMapping("/show/employee/projects")
    public List<Project> getProjectsByEmployee(HttpServletRequest request){

        Employee employee=(Employee)request.getSession().getAttribute("user");
        return  projectService.getProjectByEmployee(employee.getEid());
    }
}
