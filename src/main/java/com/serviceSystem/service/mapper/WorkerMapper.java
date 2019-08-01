package com.serviceSystem.service.mapper;

import com.serviceSystem.entity.Worker;
import com.serviceSystem.entity.dto.WorkerDto;
import com.serviceSystem.entity.enums.Role;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class WorkerMapper extends AbstractMapper<Worker, WorkerDto> {

    @Autowired
    private ModelMapper mapper;

    public WorkerMapper(){
        super(Worker.class,WorkerDto.class);
    }

    @PostConstruct
    public void setupMapping(){
        mapper.createTypeMap(Worker.class,WorkerDto.class)
                .addMappings(m -> m.skip(WorkerDto::setRole))
                .setPostConverter(toDtoConverter());
        mapper.createTypeMap(WorkerDto.class,Worker.class)
                .addMappings(m -> m.skip(Worker::setRole))
                .setPostConverter(toEntityConverter());
    }

    private Converter<WorkerDto, Worker> toEntityConverter() {
        return mappingContext -> {
            WorkerDto source = mappingContext.getSource();
            Worker destination = mappingContext.getDestination();
            mapSpecificFields(source,destination);
            return mappingContext.getDestination();
        };
    }

    private void mapSpecificFields(WorkerDto source, Worker destination) {
        destination.setRole(Role.valueOf(source.getRole()));
    }

    private Converter<Worker,WorkerDto> toDtoConverter(){
        return mappingContext -> {
            Worker source = mappingContext.getSource();
            WorkerDto destination = mappingContext.getDestination();
            mapSpecificFields(source,destination);
            return mappingContext.getDestination();
        };
    }
    private void mapSpecificFields(Worker source, WorkerDto destination){
        destination.setRole(source.getRole().name());
    }
}
