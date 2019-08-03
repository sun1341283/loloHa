package cn.offcn.service.forumpost;

import cn.offcn.entity.Evaluate;
import cn.offcn.entity.Forumpost;

import java.util.List;

public interface ForumpostService {



    public void saveForum(Forumpost forumpost);

    public List<Forumpost> getCurrentEmployeeForums(int empfk3);

    public List<Forumpost>  getAllEmployeeForumposts();

    public Forumpost getForumEvaluateByuForumId(Integer forumid);

    public void saveForumEvaluate(Evaluate evaluate);

}
