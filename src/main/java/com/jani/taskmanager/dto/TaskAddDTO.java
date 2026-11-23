package com.jani.taskmanager.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author Jani
 * @data 2026/11/23 20:59
 **/
@Data
public class TaskAddDTO {
    @NotBlank(message = "任务标题不能为空！")
    private String title;

    @NotBlank(message = "任务状态不能为空！")
    private String status;
}
