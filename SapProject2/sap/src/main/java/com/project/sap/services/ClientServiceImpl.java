package com.project.sap.services;

import com.project.sap.models.Client;
import com.project.sap.repositories.ClientRepository;
import com.project.sap.services.interfaces.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    ClientRepository clientRepository;
    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository){
        this.clientRepository=clientRepository;
    }

    public List<Client> get() {
        return clientRepository.findAll();
    }

    public Optional<Client> getById(long id) {
        return clientRepository.findById(id);
    }

    @Override
    public void deleteById(long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public void addClient(Client client) {
        clientRepository.save(client);
    }

    @Override
    public boolean hasSales(long id) {
        return this.getById(id).get().getSales().size()>0;
    }
}
