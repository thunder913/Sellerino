package com.project.sap.services.implementations;

import com.project.sap.models.Screen;
import com.project.sap.repositories.ScreenRepository;
import com.project.sap.services.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScreenServiceImpl implements ScreenService {

    @Autowired
    ScreenRepository screenRepository;

    public List<Screen> get() {
        return screenRepository.findAll();
    }

    public void add(Screen screen) {
        screen.setName(screen.getDiagonal() + " inches " + screen.getRefreshRate() + "Hz " + screen.getResponseTime() + "ms response time.");
        screenRepository.save(screen);
    }

    public void deleteById(long id) {
        screenRepository.deleteById(id);
    }

    public Optional<Screen> findById(long id) {
        return screenRepository.findById(id);
    }
}
