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

    // 把 List 放在类级别，模拟一个全局的数据库
    private final List<Task> tasks = new ArrayList<>();

    // 构造方法，初始化时就放两条假数据
    public TaskService() {
        tasks.add(new Task(1L, "学习 Spring Boot", "进行中"));
        tasks.add(new Task(2L, "复习 Git 命令", "已完成"));
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    // 新增：添加任务的方法
    public void addTask(Task task) {
        // 简单模拟一下自动生成 ID
        task.setId((long) (tasks.size() + 1));
        tasks.add(task);
    }
}
