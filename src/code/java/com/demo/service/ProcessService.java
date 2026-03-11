package com.demo.service;

import com.demo.model.Process;
import java.util.*;
import com.demo.repository.ProcessRepository;
import org.springframework.stereotype.Service;

@Service
public class ProcessService {

    private final ProcessRepository repository;

    public ProcessService(ProcessRepository repository) {
        this.repository = repository;
    }

    public List<Process> getAllProcesses() {
        return repository.findAll();
    }

    public Process createProcess(Process process) {
        return repository.save(process);
    }

    public void deleteProcess(Long id) {
        repository.delete(id);
    }
}
