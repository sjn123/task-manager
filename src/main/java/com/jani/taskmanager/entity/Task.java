package com.jani.taskmanager.entity;

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
    private String title;
    private String status;

}
