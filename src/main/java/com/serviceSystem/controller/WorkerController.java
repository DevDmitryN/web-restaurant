package com.serviceSystem.controller;

import com.serviceSystem.entity.dto.WorkerDto;
import com.serviceSystem.service.WorkerService;
import com.serviceSystem.service.mapper.WorkerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class WorkerController {

    @Autowired
    private WorkerService workerService;
    @Autowired
    private WorkerMapper workerMapper;

    @GetMapping("/workers/{id}")
    public ResponseEntity<WorkerDto> getWorker(@PathVariable("id") int id){
        return new ResponseEntity<>(workerMapper.toDto(workerService.getById(id)),HttpStatus.OK);
    }

    @GetMapping("/workers")
    public ResponseEntity<List<WorkerDto>> getWorkers(){
        return new ResponseEntity<>(workerMapper.toDtoList(workerService.getAll()), HttpStatus.OK);
    }
}
