package com.serviceSystem.controller.util.dtoConverter;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class DtoConverterImpl<Entity,EntityDTO> implements DtoConvertrer<Entity,EntityDTO>{

    private final ModelMapper modelMapper = new ModelMapper();

    private Class<Entity> entityClass;
    private Class<EntityDTO> entityDTOClass;

    public DtoConverterImpl(Class<Entity> entityClass, Class<EntityDTO> entityDTOClass) {
        this.entityClass = entityClass;
        this.entityDTOClass = entityDTOClass;
    }

    public List<EntityDTO> toDTOList(List<Entity> entities){
        List<EntityDTO> entityDTOList = new ArrayList<>();
        entities.forEach(entity -> entityDTOList.add(toDTO(entity)));
        return entityDTOList;
    }
    public EntityDTO toDTO(Entity entity){
        return  modelMapper.map(entity,entityDTOClass);
    }

    public List<Entity> fromDTOList(List<EntityDTO> entityDTOList){
        List<Entity> entities = new ArrayList<>();
        entityDTOList.forEach( entityDTO -> entities.add(fromDTO(entityDTO)));
        return entities;
    }
    public Entity fromDTO(EntityDTO entityDTO){
        return (Entity)  modelMapper.map(entityDTO,entityClass);
    }

}
