package com.serviceSystem.controller;

import com.serviceSystem.entity.Worker;
import com.serviceSystem.entity.dto.WorkerDto;
import com.serviceSystem.entity.dto.form.SignUpWorkerForm;
import com.serviceSystem.entity.dto.form.UpdatePasswordForm;
import com.serviceSystem.service.WorkerService;
import com.serviceSystem.service.mapper.WorkerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api")
public class WorkerController {

    private Logger logger = LoggerFactory.getLogger(WorkerController.class);

    @Autowired
    private WorkerService workerService;
    @Autowired
    private WorkerMapper workerMapper;

    @GetMapping("/workers/{workerId}")
    public ResponseEntity<WorkerDto> getWorker(@PathVariable("workerId") int id){
        return new ResponseEntity<>(workerMapper.toDto(workerService.getById(id)),HttpStatus.OK);
    }

    @GetMapping("/workers")
    public ResponseEntity<List<WorkerDto>> getWorkers(){
        return new ResponseEntity<>(workerMapper.toDtoList(workerService.getAll()), HttpStatus.OK);
    }
    @PostMapping("/workers")
    public ResponseEntity addWorker(@RequestBody @Valid SignUpWorkerForm signUpWorkerForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.BAD_REQUEST);
        }
        Worker worker = workerMapper.toEntity(signUpWorkerForm);
        workerService.save(worker);
        return new ResponseEntity<>(signUpWorkerForm,HttpStatus.OK);
    }
    @GetMapping("/workers/staff")
    public ResponseEntity getStaff(){
        List<WorkerDto> workers = workerMapper.toDtoList(workerService.getStaff());
        return new ResponseEntity<>(workers,HttpStatus.OK);
    }

    @PutMapping("/workers/{workerId}")
    public ResponseEntity update(@RequestBody @Valid WorkerDto workerDto,BindingResult bindingResult,
                                 @PathVariable("workerId") int workerId){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.BAD_REQUEST);
        }
        Worker worker = workerMapper.toEntity(workerDto);
        workerService.update(worker);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("/workers/{workerId}")
    public ResponseEntity updatePassword(@RequestBody @Valid UpdatePasswordForm updatePasswordForm, BindingResult bindingResult,
                                         @PathVariable("workerId") int id){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.BAD_REQUEST);
        }
        Worker worker = workerService.getById(id);
        worker.setPassword(updatePasswordForm.getNewPassword());
        logger.info("worker id = " + id + ", old password = " + updatePasswordForm.getOldPassword() + ", new = " + updatePasswordForm.getNewPassword());
        workerService.update(worker);
        return new ResponseEntity(HttpStatus.OK);
    }
}
