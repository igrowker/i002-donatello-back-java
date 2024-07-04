package com.igrowker.donatello.services;

import com.igrowker.donatello.dtos.ClienteDTO;

import java.util.List;

public interface IClienteService {

    List<ClienteDTO> listarClientes();

    ClienteDTO registrar(ClienteDTO clienteDto);

    ClienteDTO modificar(Integer id, ClienteDTO clienteDto);

    void eliminar(Integer id);
}
