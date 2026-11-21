package com.jani.taskmanager.service;

import com.jani.taskmanager.entity.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jani
 * @data 2026/11/21 21:59
 **/
@Service
public class TaskService {
    // 模拟数据库里的数据
    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1L, "学习 Spring Boot", "进行中"));
        tasks.add(new Task(2L, "复习 Git 命令", "已完成"));
        return tasks;
    }
}
