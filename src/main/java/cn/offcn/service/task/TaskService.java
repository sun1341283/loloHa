package cn.offcn.service.task;

import cn.offcn.entity.Task;
import cn.offcn.utils.OAResult;

import java.util.List;

public interface TaskService {

    public OAResult saveTask(Task task);

    public List<Task> getTaskByEmployee(Integer eid);

    public OAResult updateTaskStatus(Integer taskId, Integer status);
}
