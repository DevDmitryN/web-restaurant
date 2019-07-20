package com.serviceSystem.controller.util.dtoConverter;

import java.util.List;

public interface DtoConvertrer<Entity,EntityDTO> {
    EntityDTO toDTO(Entity entity);
    List<EntityDTO> toDTOList(List<Entity> entities);
    Entity fromDTO(EntityDTO entityDTO);
    List<Entity> fromDTOList(List<EntityDTO> entityDTOList);
}
