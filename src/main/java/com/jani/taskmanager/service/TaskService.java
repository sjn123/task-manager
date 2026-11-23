package com.jani.taskmanager.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jani.taskmanager.entity.Task;
import com.jani.taskmanager.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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

    // 分页 + 条件查询
    public Page<Task> getTasksByPage(int pageNum, int pageSize, String title, String status) {
        Page<Task> page = new Page<>(pageNum, pageSize);

        // 核心：LambdaQueryWrapper，专门用来拼条件的
        LambdaQueryWrapper<Task> wrapper = new LambdaQueryWrapper<>();

        // 魔法解析：第一个参数是一个 boolean。
        // 如果 StringUtils.hasText(title) 为 true（即前端传了关键字），才把后面的 like 条件拼进去；
        // 如果没传，这个条件直接被忽略，不会影响 SQL。
        wrapper.like(StringUtils.hasText(title), Task::getTitle, title);

        // 如果是下拉框选状态，一般用 eq (等于)
        wrapper.eq(StringUtils.hasText(status), Task::getStatus, status);

        // 加个排序，让最新的任务排在最前面（根据 id 倒序）
        wrapper.orderByDesc(Task::getId);

        // 把分页对象和条件构造器一起传进去
        taskMapper.selectPage(page, wrapper);
        return page;
    }

}
