package com.project.sap.services.interfaces;


import com.project.sap.models.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<Client> get();

    Optional<Client> getById(long id);

    void deleteById(long id);

    void addClient(Client client);

    boolean hasSales(long id);
}
