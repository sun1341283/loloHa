package cn.offcn.service.baoxiao;

import cn.offcn.entity.Baoxiao;
import cn.offcn.utils.OAResult;

import java.util.List;

public interface BaoXiaoService {

    public OAResult saveBaoXiao(Baoxiao baoxiao);

    public List<Baoxiao> getMyBaoxiaoByEid(Integer eid);

    public List<Baoxiao> getUndoBaoXiaos();

    public Baoxiao getOneBaoXiao(String bxid);

    public OAResult updateBaoxiaoByBxid(String bxid, Integer bxstatus, String pizhu, Integer empId);

    public OAResult updateBaoxiaoByBxid(Baoxiao baoxiao);
}
