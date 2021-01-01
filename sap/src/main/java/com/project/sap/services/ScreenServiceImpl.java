package com.project.sap.services;

import com.project.sap.models.Screen;
import com.project.sap.repositories.ScreenRepository;
import com.project.sap.services.interfaces.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

@Service
public class ScreenServiceImpl implements ScreenService {

    ScreenRepository screenRepository;
    @Autowired
    public ScreenServiceImpl(ScreenRepository screenRepository) {
        this.screenRepository = screenRepository;
    }

    public List<Screen> get() {
        return screenRepository.findAll();
    }

    public void add(Screen screen) {
        DecimalFormat format = new DecimalFormat("0.#");
        screen.setName(format.format(screen.getDiagonal()) + " inches " + screen.getRefreshRate() + "Hz " + format.format(screen.getResponseTime()) + "ms response time.");
        screenRepository.save(screen);
    }

    public void deleteById(long id) {
        screenRepository.deleteById(id);
    }

    public Optional<Screen> findById(long id) {
        return screenRepository.findById(id);
    }
}
