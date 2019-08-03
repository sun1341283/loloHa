package cn.offcn.service.notice;

import cn.offcn.entity.Notice;
import cn.offcn.entity.NoticeExample;
import cn.offcn.mapper.NoticeMapper;
import cn.offcn.utils.OAResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NoticeServiceImpl implements  NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    public OAResult saveNotice(Notice notice){
        notice.setNdate(new Date());
        int rows=noticeMapper.insert(notice);
        if(rows==1){
            return OAResult.ok(200,"公告添加成功");
        }
        return OAResult.ok(200,"公告添失败");
    }

    public List<Notice> getNotices(){
        NoticeExample noticeExample=new NoticeExample();
        return noticeMapper.selectByExample(noticeExample);
    }

    public List<Notice> getNoticesByTop3(){

        return noticeMapper.getNoticesByTop3();
    }

    public Notice getNoticeByNid(Integer nid){

        return noticeMapper.selectByPrimaryKey(nid);
    }

}
