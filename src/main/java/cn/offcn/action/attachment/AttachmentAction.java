package cn.offcn.action.attachment;

import cn.offcn.entity.Attachment;
import cn.offcn.service.attachment.AttachementService;
import cn.offcn.utils.OAResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author loloSun
 * @Date 2019/6/18 0018 16:44
 * @Version 1.0
 */
@Controller
@RequestMapping("/attachment")
public class AttachmentAction {
    @Autowired
    private AttachementService attachementService;

    @RequestMapping("/forward/{page}")
    public String forward(@PathVariable("page") String page){
        return "attachment/"+page;
    }

    @RequestMapping("/queryAttachment")
    @ResponseBody
    public List<Attachment> queryAnalysis(){
        return attachementService.queryAttachment();
    }
    @RequestMapping("/saveAttachment")
    @ResponseBody
    public OAResult saveAttachment(Attachment attachment, MultipartFile uploadfile){
        return attachementService.saveAttachment(attachment,uploadfile);
    }


}
