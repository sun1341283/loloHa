package cn.offcn.service.attachment.impl;

import cn.offcn.entity.Attachment;
import cn.offcn.entity.AttachmentExample;
import cn.offcn.mapper.AttachmentMapper;
import cn.offcn.service.attachment.AttachementService;
import cn.offcn.utils.OAResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Author loloSun
 * @Date 2019/6/18 0018 16:52
 * @Version 1.0
 */
@Service
public class AttachmentServiceImpl implements AttachementService {
    @Autowired
    private AttachmentMapper attachmentMapper;
    @Override
    public List<Attachment> queryAttachment() {
        final AttachmentExample attachmentExample = new AttachmentExample();
        return attachmentMapper.selectByExample(attachmentExample);
    }

    @Override
    public OAResult saveAttachment(Attachment attachment, MultipartFile uploadfile){
        String path="";
        try{
            if(uploadfile!=null){
                //处理文件上传
                String saveDir="G:\\project\\attachment";
                //原始的文件名
                String fileName=uploadfile.getOriginalFilename();
                //使用文件名来创建两个目录,把文件分散到不同的目录中
                int hashCode=fileName.hashCode();

                int d1=hashCode & 0xf;
                int d2=(hashCode>>4) & 0xf;
                saveDir=saveDir+"\\"+d1+"\\"+d2;
                File saveDirFile=new File(saveDir);
                //如果目录不存在就创建
                if(!saveDirFile.exists()) {saveDirFile.mkdirs();}
                fileName= UUID.randomUUID().toString()+"&"+fileName;
                File saveFile=new File(saveDirFile,fileName);
                uploadfile.transferTo(saveFile);
                path="file/"+d1+"/"+d2+"/"+fileName;
            }
        }catch(Exception e){
            e.printStackTrace();
            return OAResult.ok(400,"上传附件失败");
        }

        //设置上传的文件的路径
        attachment.setPath(path);
        attachment.setUploadtime(new Date());
        int rows = attachmentMapper.insert(attachment);
        if(rows==1){
            return OAResult.ok(200,"附件保存成功");
        }
        return OAResult.ok(400,"附件保存失败");

    }
}
