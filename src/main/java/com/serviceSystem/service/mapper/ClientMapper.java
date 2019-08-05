package com.serviceSystem.service.mapper;

import com.serviceSystem.entity.dto.form.SignUpClientForm;
import com.serviceSystem.entity.Client;
import com.serviceSystem.entity.dto.ClientDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
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
        mapper.createTypeMap(SignUpClientForm.class,Client.class)
                .addMappings(m -> m.skip(Client::setOrders));
    }

    public Client toEntity(SignUpClientForm signUpClientForm){
        return mapper.map(signUpClientForm,Client.class);
    }

}
