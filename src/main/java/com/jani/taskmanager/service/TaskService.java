package com.jani.taskmanager.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jani.taskmanager.entity.PageResult;
import com.jani.taskmanager.entity.Task;
import com.jani.taskmanager.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    //分页 + 条件查询 + Redis
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ObjectMapper objectMapper; // Spring Boot 自带的 JSON 转换工具
    public PageResult<Task> getTasksByPage(int pageNum, int pageSize, String title, String status) {
        // 1. 拼接缓存 Key
        String cacheKey = "task:page:" + pageNum + ":" + pageSize + ":" + title + ":" + status;

        // 2. 查 Redis
        String cacheData = stringRedisTemplate.opsForValue().get(cacheKey);
        if (cacheData != null) {
            System.out.println("✅ 命中缓存！从 Redis 返回数据");
            try {
                // 注意这里的导包必须是 com.fasterxml.jackson.core.type.TypeReference
                return objectMapper.readValue(cacheData, new TypeReference<PageResult<Task>>() {});
            } catch (Exception e) {
                System.out.println("⚠️ 缓存数据解析失败，将查询数据库");
            }
        }

        // 3. 查数据库 (未命中缓存，或者缓存解析失败)
        System.out.println("❌ 未命中缓存，查询 MySQL 数据库...");
        Page<Task> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Task> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasText(title), Task::getTitle, title);
        wrapper.eq(StringUtils.hasText(status), Task::getStatus, status);
        wrapper.orderByDesc(Task::getId);

        taskMapper.selectPage(page, wrapper);
        PageResult<Task> result = new PageResult<>(page.getRecords(), page.getTotal());

        // 4. 回写 Redis，设置 5 分钟过期
        try {
            String json = objectMapper.writeValueAsString(result);
            stringRedisTemplate.opsForValue().set(cacheKey, json, 5, TimeUnit.MINUTES);
        } catch (Exception e) {
            System.out.println("⚠️ 写入 Redis 失败");
        }

        // 5. 返回数据库查到的结果
        return result;
    }
}
