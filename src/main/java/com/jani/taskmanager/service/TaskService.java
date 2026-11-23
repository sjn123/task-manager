package com.jani.taskmanager.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jani.taskmanager.entity.Task;
import com.jani.taskmanager.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jani
 * @data 2026/11/21 21:59
 **/
@Service
public class TaskService {

    @Autowired
    private TaskMapper taskMapper;

    // 查询
    public List<Task> getAllTasks() {
        return taskMapper.selectList(null);
    }

    // 新增一个
    public void addTask(Task task) {
        taskMapper.insert(task);
    }

    // 新增一群
    @Transactional(rollbackFor = Exception.class)
    public void addTasks(List<Task> list) {
        for (Task task : list) {
            taskMapper.insert(task);
        }
    }

    // 修改一个
    public void updateTask(Task task) {
        taskMapper.updateById(task);
    }

    // 删除一个
    public void deleteTask(long id) {
        taskMapper.deleteById(id);
    }

    // 分页查询
    public Page<Task> getTasksByPage(int pageNum, int pageSize) {
        // 1. 创建一个 Page 对象，传入当前页码和每页条数
        Page<Task> page = new Page<>(pageNum, pageSize);

        // 2. 调用 MyBatis-Plus 提供的 selectPage 方法
        // 第一个参数是 page 对象，第二个参数是查询条件（暂时传 null 代表查全部）
        taskMapper.selectPage(page, null);

        // 3. 返回这个 Page 对象（里面不仅有数据，还有总条数、总页数）
        return page;
    }

}
