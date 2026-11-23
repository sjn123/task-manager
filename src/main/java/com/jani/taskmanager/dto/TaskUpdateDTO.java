package com.jani.taskmanager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author Jani
 * @data 2026/11/23 20:59
 **/
@Data
public class TaskUpdateDTO {
    @NotNull(message = "修改时ID不能为空！")
    private Long id;

    @NotBlank(message = "任务标题不能为空！")
    private String title;

    @NotBlank(message = "任务状态不能为空！")
    private String status;
}
