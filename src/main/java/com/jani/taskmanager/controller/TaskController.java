package com.jani.taskmanager.controller;

import com.jani.taskmanager.entity.Task;
import com.jani.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Jani
 * @data 2026/11/21 22:00
 **/
@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<Task> getTasks() {
        return taskService.getAllTasks();
    }

    // 新增：处理 POST 请求
    @PostMapping
    public String addTask(@Valid @RequestBody Task task) {
        taskService.addTask(task);
        return "任务添加成功！";
    }
}
