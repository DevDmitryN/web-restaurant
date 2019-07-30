package com.serviceSystem.service.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractMapper<E,D> {

    @Autowired
    private ModelMapper mapper;

    private Class<E> entityClass;
    private Class<D> dtoClass;

    public AbstractMapper(Class<E> entityClass, Class<D> dtoClass) {
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
    }

    public D toDto(E entity){
        return entity == null ? null : mapper.map(entity,dtoClass);
    }
    public E toEntity(D dto){
        return dto == null ? null : mapper.map(dto,entityClass);
    }
    public List<D> toDtoList(List<E> entities){
        return entities == null ? null : entities.stream().map(entity -> toDto(entity)).collect(Collectors.toList());
    }
    public List<E> toEntityList(List<D> dtoList){
        return dtoList == null ? null : dtoList.stream().map(dto -> toEntity(dto)).collect(Collectors.toList());
    }
}
