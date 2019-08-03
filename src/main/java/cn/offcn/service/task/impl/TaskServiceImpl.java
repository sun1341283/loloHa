package cn.offcn.service.task.impl;

import cn.offcn.entity.Task;
import cn.offcn.mapper.TaskMapper;
import cn.offcn.service.task.TaskService;
import cn.offcn.utils.OAResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskMapper taskMapper;
    @Override
    public OAResult saveTask(Task task){
       //设置任务的状态
        task.setStatus(0);
       int rows= taskMapper.insert(task);
       if(rows==1){
           return OAResult.ok(200,"任务分配成功");
       }
        return OAResult.ok(200,"任务分配失败");
    }
    @Override
    public List<Task> getTaskByEmployee(Integer eid){
       return  taskMapper.getTaskByEmployee(eid);
    }
    @Override
    public OAResult updateTaskStatus(Integer taskId,Integer status){

        //根据taskId查询
        Task task=taskMapper.selectByPrimaryKey(taskId);
        task.setStatus(status);
        int rows=taskMapper.updateByPrimaryKey(task);
        if(rows==1){
            return OAResult.ok(200,"任务状态更改成功");
        }
        return OAResult.ok(200,"任务状态更改失败");
    }
}
