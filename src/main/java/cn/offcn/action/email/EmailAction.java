package cn.offcn.action.email;

import cn.offcn.entity.Email;
import cn.offcn.entity.Employee;
import cn.offcn.service.mail.EmailService;
import cn.offcn.utils.OAResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.Properties;

@Controller
@RequestMapping("/email")
/*
 *    发送邮件的过程中如果出现了 554异常的问题(发送的内容不安全) 给自己抄送一份就可以了
 *    出现  connection reset 连接问题(过一段时间在去使用当前程序发送邮件) 频繁发送失败邮件，邮箱服务器断开连接
 * */
public class EmailAction {

    @Autowired
    private EmailService emailService;

    @RequestMapping("/forward/{page}")
        public String forwardEmailPage(@PathVariable("page") String page){

            return "mail/"+page;
    }


    @RequestMapping("/sendMail")
    @ResponseBody
    public OAResult sendMail(Email email, String reemp, MultipartFile file, HttpServletRequest request) throws Exception {
        System.out.println(email.getEmailcontent()+":"+email.getTitle());
        System.out.println(reemp+"===");

        // 发件人的邮箱和密码
        String myEmailAccount = "cctvtype@163.com";
        String myEmailPassword = "cctvtype001";// 邮箱密码
        String [] info = reemp.split(";");
        // 收件人邮箱
        String receiveMailAccount = info[0];
        // 1. 创建参数配置, 用于连接邮件服务器的参数配置
        Properties props = new Properties();  // 参数配置
        props.setProperty("mail.transport.protocol", "smtp");// 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", "smtp.163.com");// 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");// 需要请求认证
        // 创建验证器
        Authenticator auth = new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                // 密码验证
                return new PasswordAuthentication("cctvtype", "cctv100");// 邮箱的授权码
            }
        };

        //当前程序和网易邮件服务器之间的交互会话
        Session session = Session.getInstance(props,auth);
        //开启debug模式  查看邮件的发送状态
        session.setDebug(true);

        //创建一个邮件对象
        MimeMessage message = new MimeMessage(session);
        //指明邮件的发件人
        message.setFrom(new InternetAddress(myEmailAccount));
        //给自己的当前抄送一份
        message.addRecipients(MimeMessage.RecipientType.CC, InternetAddress.parse(myEmailAccount));
        //指明邮件的收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiveMailAccount));
        //邮件的标题
        message.setSubject(email.getTitle(), "UTF-8");

        // 创建一个封装多个消息容器 map
        Multipart multipart = new MimeMultipart();

        // 创建消息部分
        BodyPart messageBodyPart = new MimeBodyPart();
        // 包装文本内容
        messageBodyPart.setText(email.getEmailcontent());

        // 设置文本消息部分
        multipart.addBodyPart(messageBodyPart);

        // 文件上传获取文件路径
        String  path = "C:\\Users\\Administrator\\Desktop\\小蓝猫";
        if(file.getSize()>0){
            path += file.getOriginalFilename();
            File nfile =  new File(path);
            file.transferTo(nfile);
            // 附件部分
            messageBodyPart = new MimeBodyPart();
            // 设置要发送附件的文件路径
            DataSource source = new FileDataSource(path);
            messageBodyPart.setDataHandler(new DataHandler(source));
            // messageBodyPart.setFileName(filename);
            // 处理附件名称中文（附带文件路径）乱码问题
            messageBodyPart.setFileName(MimeUtility.encodeText(path));
            //邮件内容添加到容器中
            multipart.addBodyPart(messageBodyPart);
        }

        // 5. Content: 邮件正文（可以使用html标签）
        message.setContent(multipart);
        // 6. 设置发件时间
        message.setSentDate(new Date());
        // 7. 保存设置
        message.saveChanges();

        //创建发送邮件对象
        Transport transport = session.getTransport();
        //设定当前邮箱的名称和密码
        transport.connect(myEmailAccount, myEmailPassword);
        //发送邮件对象和抄送人
        transport.sendMessage(message, message.getAllRecipients());
        //关闭当前程序和邮件服务器之间的连接
        transport.close();

        //数据库中应该出现一条发送记录
        //收件人的id
        email.setEmpFk2(email.getEmpFk2());

        //设定的发送时间
        email.setSendtime(new Date());
        //发件人登录用户的id
        Employee employee=(Employee)request.getSession().getAttribute("user");
        email.setEmpFk(employee.getEid());

        OAResult oaResult = emailService.saveEmail(email);

        return oaResult;
    }
}
