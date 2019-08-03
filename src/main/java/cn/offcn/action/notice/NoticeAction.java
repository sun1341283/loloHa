package cn.offcn.action.notice;

import cn.offcn.entity.Notice;
import cn.offcn.service.notice.NoticeService;
import cn.offcn.utils.OAResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/notice")
public class NoticeAction {


    @Autowired
    private NoticeService noticeService;

    @RequestMapping("/show/{page}")
    public String showNoticePage(@PathVariable("page") String page){

        return "notice/"+page;
    }

    @RequestMapping("/show")
    public String showNotice(Model model){

       List<Notice> noticeList=noticeService.getNotices();
       model.addAttribute("noticeList",noticeList);
        return "notice/notice";
    }

    @ResponseBody
    @RequestMapping("/add")
    public OAResult addNotice(Notice notice){

        return noticeService.saveNotice(notice);
    }

    @ResponseBody
    @RequestMapping("/showTop3")
    public List<Notice> getNoticesByTop3(){

        return noticeService.getNoticesByTop3();
    }

    @ResponseBody
    @RequestMapping("/getOneNotice")
    public Notice getNoticeByNid(Integer nid){

        return noticeService.getNoticeByNid(nid);
    }
}
