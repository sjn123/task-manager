package com.jani.taskmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author Jani
 * @data 2026/11/23 19:49
 **/
@Data
@AllArgsConstructor
public class PageResult<T> {
    private List<T> records; // 当前页数据
    private Long total;      // 总条数
}
