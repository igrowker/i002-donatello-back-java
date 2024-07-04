package com.igrowker.donatello.services.impl;

import com.igrowker.donatello.dtos.ClienteDTO;
import com.igrowker.donatello.exceptions.NotFoundException;
import com.igrowker.donatello.mappers.ClienteMapper;
import com.igrowker.donatello.models.Cliente;
import com.igrowker.donatello.repositories.IClienteRepository;
import com.igrowker.donatello.repositories.UserRepository;
import com.igrowker.donatello.services.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private IClienteRepository repoCliente;

    @Autowired
    private UserRepository repoUsuario;

    @Autowired
    private ClienteMapper clienteMapper;

    @Override
    public List<ClienteDTO> listarClientes() {
        return repoCliente.findAll().stream().map((cliente)->clienteMapper.clienteToClienteDTO(cliente)).collect(Collectors.toList());
    }

    @Override
    public ClienteDTO registrar(ClienteDTO clienteDto) {
        Cliente cliente = clienteMapper.clienteDTOToCliente(clienteDto);
        cliente.setUsuario(repoUsuario.getReferenceById(clienteDto.getUsuarioID()));
        return clienteMapper.clienteToClienteDTO(repoCliente.save(cliente));
    }

    @Override
    public ClienteDTO modificar(Integer id, ClienteDTO clienteDto) {
        Cliente cliente = clienteMapper.clienteDTOToCliente(clienteDto);
        cliente.setUsuario(repoUsuario.getReferenceById(clienteDto.getUsuarioID()));
        cliente.setId(id);
        return clienteMapper.clienteToClienteDTO(repoCliente.save(cliente));
    }

    @Override
    public void eliminar(Integer id) {
        if(repoCliente.existsById(id))repoCliente.deleteById(id);
        else throw new NotFoundException("ID: "+ id);
    }
}
