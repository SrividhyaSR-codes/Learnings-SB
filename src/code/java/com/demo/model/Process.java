package com.demo.model;

import lombok.Data;

@Data
public class Process {

    private Long id;
    private String name;
    private String priority;

    public Process() {}

    public Process(Long id, String name, String priority) {
        this.id = id;
        this.name = name;
        this.priority = priority;
    }
}
