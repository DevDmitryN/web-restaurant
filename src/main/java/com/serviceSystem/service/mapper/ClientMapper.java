package com.serviceSystem.service.mapper;

import com.serviceSystem.entity.Client;
import com.serviceSystem.entity.dto.ClientDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public class ClientMapper extends AbstractMapper<Client, ClientDto> {
    @Autowired
    private ModelMapper mapper;

    public ClientMapper(){
        super(Client.class,ClientDto.class);
    }

    @PostConstruct
    public void setupMapper(){
        mapper.createTypeMap(ClientDto.class,Client.class)
                .addMappings(m -> m.skip(Client::setOrders));
    }

}
