package com.project.sap.services;

import com.project.sap.models.Screen;

import java.util.List;
import java.util.Optional;

public interface ScreenService {
    List<Screen> get();

    void add(Screen screen);

    void deleteById(long id);

    Optional<Screen> findById(long id);
}
