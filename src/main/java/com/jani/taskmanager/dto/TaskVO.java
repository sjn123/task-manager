package com.jani.taskmanager.dto;

import lombok.Data;

/**
 * @author Jani
 * @data 2026/11/23 21:01
 **/
@Data
public class TaskVO {
    private Long id;
    private String title;
    private String status;
}
