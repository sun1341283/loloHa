package cn.offcn.service.mail;

import cn.offcn.entity.Email;
import cn.offcn.utils.OAResult;

public interface EmailService {

    public OAResult saveEmail(Email email);
}
