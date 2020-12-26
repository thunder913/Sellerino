package com.project.sap.services;


import com.project.sap.models.Client;

import java.util.List;

public interface ClientService {
    List<Client> get();

    Client getById(long id);

    void deleteById(long id);

    void addClient(Client client);

}
