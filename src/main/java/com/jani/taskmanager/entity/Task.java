package com.jani.taskmanager.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jani
 * @data 2026/11/21 21:47
 **/
@Data
@NoArgsConstructor // 生成无参构造
@AllArgsConstructor // 生成全参构造
@TableName("task")
public class Task {
    @TableId(type = IdType.AUTO)
    private Long id;
    @NotBlank(message = "任务标题不能为空！")
    private String title;
    @NotBlank(message = "任务标题不能为空！")
    private String status;

}
