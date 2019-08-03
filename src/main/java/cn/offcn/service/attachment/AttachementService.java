package cn.offcn.service.attachment;

import cn.offcn.entity.Attachment;
import cn.offcn.utils.OAResult;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author loloSun
 * @Date 2019/6/18 0018 16:52
 * @Version 1.0
 */
@Service
public interface AttachementService {
    List<Attachment> queryAttachment();

    OAResult saveAttachment(Attachment attachment, MultipartFile uploadfile);
}
