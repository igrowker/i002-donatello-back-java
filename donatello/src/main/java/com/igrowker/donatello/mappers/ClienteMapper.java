package com.igrowker.donatello.mappers;

import com.igrowker.donatello.dtos.ClienteDTO;
import com.igrowker.donatello.models.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    @Mapping(target = "usuario", ignore = true)
    Cliente clienteDTOToCliente(ClienteDTO clienteDto);

    @Mapping(target = "usuarioID", source = "cliente.usuario.id")
    ClienteDTO clienteToClienteDTO(Cliente cliente);
}
