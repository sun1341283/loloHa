package cn.offcn.service.project;

import cn.offcn.entity.Project;
import cn.offcn.utils.OAResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author loloSun
 * @Date 2019/6/17 0017 17:19
 * @Version 1.0
 */
@Service
public interface ProjectService {

    OAResult saveProject(Project project);

    List<Project> query();

    List<Project> getProjectsNotNeed();

    List<Project> getProjectsNeed();

    List<Project> getProjectByModule();

    List<Project> getProjectByEmployee(Integer eid);
}
