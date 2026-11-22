package com.jani.taskmanager.service;

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
}
