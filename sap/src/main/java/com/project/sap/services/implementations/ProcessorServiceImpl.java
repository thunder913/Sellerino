package com.project.sap.services.implementations;

import com.project.sap.models.Processor;
import com.project.sap.repositories.ProcessorRepository;
import com.project.sap.services.ProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProcessorServiceImpl implements ProcessorService {
    @Autowired
    private ProcessorRepository processorRepository;

    public List<Processor> get() {
        return processorRepository.findAll();
    }

    public void add(Processor processor) {
        processor.setName(processor.getManufacturer() + " " + processor.getModel() + " " + processor.getCores() + " " + processor.getSpeed() + "GHz");
        processorRepository.save(processor);
    }

    public void deleteById(long id) {
        processorRepository.deleteById(id);
    }

    public Optional<Processor> findById(long id) {
        return processorRepository.findById(id);
    }


}
