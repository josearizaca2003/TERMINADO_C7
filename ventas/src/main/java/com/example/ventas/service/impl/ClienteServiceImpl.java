package com.example.ventas.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ventas.entity.Cliente;
import com.example.ventas.repository.ClienteRepository;
import com.example.ventas.service.ClienteService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> buscar(String name, LocalDateTime createdAt, LocalDateTime updatedAt, String tdocumento) {
        return clienteRepository.buscarPorParametros(name, createdAt, updatedAt, tdocumento);
    }

    @Override
    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente guardar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente actualizar(Cliente cliente) {
        // Primero, obtén la entidad existente desde la base de datos
        Cliente clienteExistente = clienteRepository.findById(cliente.getId())
                .orElseThrow(() -> new EntityNotFoundException("cliente no encontrada"));

        clienteExistente.setEmail(cliente.getEmail());
        clienteExistente.setPhone(cliente.getPhone());
        clienteExistente.setName(cliente.getName());
        clienteExistente.setPaterno(cliente.getPaterno());
        clienteExistente.setMaterno(cliente.getMaterno());
        clienteExistente.setTdocumento(cliente.getTdocumento());
        clienteExistente.setDireccion(cliente.getDireccion());
        clienteExistente.setPostal(cliente.getPostal());
        clienteExistente.setTdatos(cliente.getTdatos());
        return clienteRepository.save(clienteExistente);
    }

    @Override
    public Optional<Cliente> listarPorId(Integer id) {
        return clienteRepository.findById(id);
    }

    @Override
    public void eliminarPorId(Integer id) {
        clienteRepository.deleteById(id);
    }
}
