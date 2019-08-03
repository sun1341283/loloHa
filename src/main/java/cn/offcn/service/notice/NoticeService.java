package cn.offcn.service.notice;

import cn.offcn.entity.Notice;
import cn.offcn.utils.OAResult;

import java.util.List;

public interface NoticeService {

    public OAResult saveNotice(Notice notice);

    public List<Notice> getNotices();

    public List<Notice> getNoticesByTop3();


    public Notice getNoticeByNid(Integer nid);
}