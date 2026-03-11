package com.demo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.*;
import com.demo.model.Process;
import com.demo.service.ProcessService;

@RestController
@RequestMapping("/process")
@CrossOrigin(origins = "http://localhost:5173")
public class ProcessController {
     private final ProcessService service;

    public ProcessController(ProcessService service) {
        this.service = service;
    }

    @GetMapping
    public List<Process> getProcesses() {
        return service.getAllProcesses();
    }

    @PostMapping
    public Process createProcess(@RequestBody Process process) {
        return service.createProcess(process);
    }

    @DeleteMapping("/{id}")
    public void deleteProcess(@PathVariable Long id) {
        service.deleteProcess(id);
    }
}
