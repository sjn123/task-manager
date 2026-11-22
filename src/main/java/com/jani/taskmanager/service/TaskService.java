package com.jani.taskmanager.service;

import com.jani.taskmanager.entity.Task;
import com.jani.taskmanager.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jani
 * @data 2026/11/21 21:59
 **/
@Service
public class TaskService {

    @Autowired
    private TaskMapper taskMapper;

    public List<Task> getAllTasks(){
        return taskMapper.selectList(null);
    }

    public void addTask(Task task) {
        taskMapper.insert(task);
    }
}
