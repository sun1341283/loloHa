package cn.offcn.action.baoxiao;

import cn.offcn.entity.Baoxiao;
import cn.offcn.entity.Employee;
import cn.offcn.service.baoxiao.BaoXiaoService;
import cn.offcn.utils.OAResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/baoxiao")
public class BaoXiaoAction {

    @Autowired
    private BaoXiaoService baoXiaoService;

    @RequestMapping("/baoxianPage")
    public String forwardBaoXiaoPage(){
      return "baoxiao/mybaoxiao-add";
    }

    @RequestMapping("/mybaoxiao")
    public String  showMyBaoxiao(HttpSession session, Model model){
        Employee employee=(Employee)session.getAttribute("user");
        List<Baoxiao> baoxiaoList=baoXiaoService.getMyBaoxiaoByEid(employee.getEid());
        model.addAttribute("baoxiaoList",baoxiaoList);
        return "baoxiao/mybaoxiao-base";
    }

    @ResponseBody
    @RequestMapping("/add")
    public OAResult addBaoXiao(Baoxiao baoxiao, HttpSession session){
        Employee employee=(Employee)session.getAttribute("user");
        baoxiao.setEmpFk(employee.getEid());
        return baoXiaoService.saveBaoXiao(baoxiao);
    }

    @RequestMapping("/undo/baoxiaos")
    public String showUndaoBaoxiaos(Model model){
        List<Baoxiao> baoxiaoList=baoXiaoService.getUndoBaoXiaos();
        model.addAttribute("baoxiaoList",baoxiaoList);
        return "baoxiao/baoxiao-task";
    }

    @RequestMapping("/showone/{page}")
    public String showOneBaoxiao(@PathVariable("page") String page, String bxid, Model model){

        Baoxiao baoxiao=baoXiaoService.getOneBaoXiao(bxid);
        model.addAttribute("baoxiao",baoxiao);
        return "baoxiao/"+page;
    }

    @ResponseBody
    @RequestMapping("/shenpi")
    public OAResult baoxiaoShenpi(String bxid,Integer bxstatus,String pizhu,HttpSession session){

        Employee employee=(Employee)session.getAttribute("user");
        return  baoXiaoService.updateBaoxiaoByBxid(bxid,bxstatus,pizhu,employee.getEid());

    }

    @ResponseBody
    @RequestMapping("/update")
    public OAResult updateBaoxiaoByBxid(Baoxiao baoxiao){

        return baoXiaoService.updateBaoxiaoByBxid(baoxiao);
    }

}
