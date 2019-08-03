package cn.offcn.service.project.imp;

import cn.offcn.entity.Project;
import cn.offcn.entity.ProjectExample;
import cn.offcn.mapper.ProjectMapper;
import cn.offcn.utils.OAResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author loloSun
 * @Date 2019/6/17 0017 17:25
 * @Version 1.0
 */
@Service
public class ProjectServiceImpl implements cn.offcn.service.project.ProjectService {
    @Autowired
    private ProjectMapper projectMapper;
    @Override
    public OAResult saveProject(Project project) {
        final int insert = projectMapper.insert(project);
        if (insert == 1){
            return OAResult.ok(200,"项目添加成功");
        }else {
            return OAResult.ok(400,"项目添加失败");
        }
    }

    @Override
    public List<Project> query() {
        final ProjectExample example = new ProjectExample();
        return projectMapper.selectByExample(example);
    }

    @Override
    public List<Project> getProjectsNotNeed(){
        return projectMapper.getPorjectsNotNeed();
    }
    @Override
    public List<Project> getProjectsNeed(){

        return projectMapper.getPorjectsNeed();
    }
    @Override
    public List<Project> getProjectByModule(){
        return projectMapper.getProjectByModule();
    }

    @Override
    public List<Project> getProjectByEmployee(Integer eid){

        return  projectMapper.getProjectByEmpoyee(eid);
    }
}
