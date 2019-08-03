package cn.offcn.action.message;

import cn.offcn.entity.Employee;
import cn.offcn.entity.Msg;
import cn.offcn.service.message.impl.MsgJob;
import cn.offcn.utils.OAResult;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Service
@RequestMapping("/message")
public class MessageAction {

    @RequestMapping("/forward/{page}")
    public String forwardMessagePage(@PathVariable("page") String page){

        return "message/"+page;
    }

    @ResponseBody
    @RequestMapping("/sendMsg")
    public OAResult sendMsg(Msg msg, HttpSession session)throws  Exception{
        msg.setMark(0);

        Employee employee=(Employee) session.getAttribute("user");
        msg.setSendp(employee.getEid());

        //1.创建调用Scheduler的工厂
        SchedulerFactory sf = new StdSchedulerFactory();
        //2.从工厂中获取调度器实例
        Scheduler scheduler = sf.getScheduler();

        //3.创建JobDetail
        JobDetail jb = JobBuilder.newJob(MsgJob.class)
                .withIdentity("ramJob", "ramGroup") //job 的name和group(名称自己定义)
                .build();

        long time1 = msg.getMsgtime().getTime() - System.currentTimeMillis();
        System.out.println(time1+"\t"+msg.getMsgtime().getTime());
        long time = System.currentTimeMillis() + time1;
        System.out.println(time+"*****************************");
        Date statTime = new Date(time);

        //4.创建Trigger
        //使用SimpleScheduleBuilder或者CronScheduleBuilder
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("ramTrigger", "ramGroup")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule())
                .startAt(statTime)//设定任务的启动时间
                .build();
        //给我们的job方法传递参数
        jb.getJobDataMap().put("msg",msg);
        //使用调度器将我们的job和trigger绑定到一起
        scheduler.scheduleJob(jb,trigger);

        if(!scheduler.isShutdown()){
            scheduler.start();
            return OAResult.ok(200,"定时消息已启动");
        }
        return OAResult.ok(400,"定时消息启动失败");

    }

}
