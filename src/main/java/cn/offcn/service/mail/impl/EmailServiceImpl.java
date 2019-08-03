package cn.offcn.service.mail.impl;

import cn.offcn.entity.Email;
import cn.offcn.mapper.EmailMapper;
import cn.offcn.service.mail.EmailService;
import cn.offcn.utils.OAResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private EmailMapper emailMapper;

    public OAResult saveEmail(Email email) {
       int rows= emailMapper.insert(email);
       if(rows>0){
           return OAResult.ok(200,"邮箱发送成功");
       }
        return OAResult.ok(200,"邮箱发送失败");
    }
}
