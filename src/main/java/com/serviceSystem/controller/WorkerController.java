package com.serviceSystem.controller;

import com.serviceSystem.entity.Worker;
import com.serviceSystem.entity.dto.WorkerDto;
import com.serviceSystem.entity.dto.form.SignUpWorkerForm;
import com.serviceSystem.entity.dto.form.UpdatePasswordForm;
import com.serviceSystem.exception.BindingResultException;
import com.serviceSystem.exception.NotCorrespondingIdException;
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
@RequestMapping("/api/v1")
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
            throw new BindingResultException(bindingResult.getAllErrors());
        }
        Worker worker = workerMapper.toEntity(signUpWorkerForm);
        workerService.save(worker);
        return new ResponseEntity<>(signUpWorkerForm,HttpStatus.CREATED);
    }
    @GetMapping("/workers/staff")
    public ResponseEntity getStaff(){
        List<WorkerDto> workers = workerMapper.toDtoList(workerService.getStaff());
        return new ResponseEntity<>(workers,HttpStatus.OK);
    }

    @PutMapping("/workers/{workerId}")
    public ResponseEntity update(@RequestBody @Valid WorkerDto workerDto,BindingResult bindingResult,
                                 @PathVariable("workerId") int workerId){
        if(workerId != workerDto.getId()){
            throw new NotCorrespondingIdException("Id in json doesn't correspond id in url");
        }
        if(bindingResult.hasErrors()){
            throw new BindingResultException(bindingResult.getAllErrors());
        }
        Worker worker = workerMapper.toEntity(workerDto);
        workerService.updateExceptPassword(worker);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("/workers/{workerId}")
    public ResponseEntity updatePassword(@RequestBody @Valid UpdatePasswordForm updatePasswordForm, BindingResult bindingResult,
                                         @PathVariable("workerId") int id){
        if(bindingResult.hasErrors()){
            throw new BindingResultException(bindingResult.getAllErrors());
        }
        logger.info("worker id = " + id + ", old password = " + updatePasswordForm.getOldPassword() + ", new = " + updatePasswordForm.getNewPassword());
        Worker worker = new Worker();
        worker.setId(id);
        worker.setPassword(updatePasswordForm.getNewPassword());
        workerService.updateOnlyPassword(worker);
        return new ResponseEntity(HttpStatus.OK);
    }
}
