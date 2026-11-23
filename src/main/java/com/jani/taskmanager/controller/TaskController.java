package com.jani.taskmanager.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jani.taskmanager.entity.PageResult;
import com.jani.taskmanager.entity.Result;
import com.jani.taskmanager.entity.Task;
import com.jani.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import org.apache.ibatis.annotations.Delete;
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
    public Result<List<Task>> getTasks() {
        List<Task> list = taskService.getAllTasks();
        return Result.success(list);
    }

    // 新增：处理 POST 请求
    @PostMapping
    public Result<Task> addTask(@Valid @RequestBody Task task) {
        taskService.addTask(task);
        return Result.success(task);
    }

    // 新增：批量插入接口
    @PostMapping("/batch")
    public Result<List<Task>> addTasks(@RequestBody @Valid List<Task> tasks) {
        taskService.addTasks(tasks);
        return Result.success(tasks); //
    }

    // 新增：处理 PUT 请求
    @PutMapping
    public Result<Task> updateTask(@Valid @RequestBody Task task) {
        System.out.println("前端传过来的对象：" + task);
        // 如果前端没传 ID，直接返回错误提示
        if (task.getId() == null) {
            return Result.fail(400, "修改失败，ID不能为空！");
        }
        taskService.updateTask(task);
        return Result.success(task);
    }

    // 新增：处理 Delete 请求
    @DeleteMapping("/{id}")
    public Result<Task> deleteTask(@PathVariable Long id) {
        // @PathVariable 意思是把 URL 里的 {id} 提取出来赋给这个参数
        taskService.deleteTask(id);
        return Result.success(null);
    }

    // 分页查询接口
    @GetMapping("/page")
    public Result<PageResult<Task>> getPage(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "5") int pageSize,
            // 加上这两个参数，required = false 表示前端可以传，也可以不传
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String status
    ) {
        Page<Task> page = taskService.getTasksByPage(pageNum, pageSize, title, status);
        return Result.success(new PageResult<>(page.getRecords(), page.getTotal()));
    }
}
