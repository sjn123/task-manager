package com.jani.taskmanager.entity;

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
public class Task {
    private Long id;
    @NotBlank(message = "任务标题不能为空！")
    private String title;
    @NotBlank(message = "任务标题不能为空！")
    private String status;

}
