package cn.offcn.action.archives;

import cn.offcn.entity.Employee;
import cn.offcn.service.archives.ArchivesService;
import cn.offcn.utils.OAResult;
import org.omg.CORBA.ORB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/archives")
public class ArchivesAction {

    @Autowired
    private ArchivesService archivesService;

    @RequestMapping("/addArchivesPage")
    public String addArchivesPage(){

        return "archives/archives-add";
    }

    @RequestMapping("/person/archivesPage")
    public String fowardPersonArchivesPage(){

        return "archives/myarchives";
    }

    @RequestMapping("/show")
    public String showArchives(){

        return "archives/archives";
    }

    @ResponseBody
    @RequestMapping("/person/archives")
    public Employee getEmployeeArchivesByEid(HttpSession session){

        Employee employee=(Employee) session.getAttribute("user");
        return archivesService.getEmployeeArchivesByEid(employee.getEid());
    }

    @ResponseBody
    @RequestMapping("import/archives/data")
    public OAResult importArchivesData(MultipartFile files){

        return archivesService.getAndSaveArchives(files);
    }
}
