package com.project.sap.services.implementations;

import com.project.sap.models.RAM;
import com.project.sap.repositories.RAMRepository;
import com.project.sap.services.RAMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RAMServiceImpl implements RAMService {

    @Autowired
    RAMRepository ramRepository;

    public List<RAM> get() {
        return ramRepository.findAll();
    }

    public void add(RAM ram) {
        ram.setName(ram.getManufacturer() + " - " + ram.getMemory() + "GB");
        ramRepository.save(ram);
    }

    public void deleteById(long id) {
        ramRepository.deleteById(id);
    }

    public Optional<RAM> findById(long id) {
        return ramRepository.findById(id);
    }
}
