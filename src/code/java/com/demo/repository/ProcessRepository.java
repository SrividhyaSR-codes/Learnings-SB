package com.demo.repository;

import java.util.*;

import com.demo.model.Process;
import org.springframework.stereotype.Repository;

@Repository
public class ProcessRepository {

     private Map<Long, Process> db = new HashMap<>();

    public List<Process> findAll() {
        return new ArrayList<>(db.values());
    }

    public Process save(Process process) {
        db.put(process.getId(), process);
        return process;
    }

    public void delete(Long id) {
        db.remove(id);
    }
}
