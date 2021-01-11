package com.project.sap;

import com.project.sap.models.Screen;
import com.project.sap.repositories.ScreenRepository;
import com.project.sap.services.interfaces.ScreenService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ServiceTests {

    @Autowired
    ScreenService screenService;
    @Autowired
    ScreenRepository screenRepository;

    private static Screen screen = new Screen();

    @BeforeEach
    public void createScreen(){
        screen.setDiagonal(17.3f);
        screen.setRefreshRate(1);
        screen.setResponseTime(1);
    }

    @Test
    public void screenServiceAddWorksCorrectly(){
        screenService.add(screen);
        List<Screen> allScreens = screenService.get();
        Screen screen1 = allScreens.get(allScreens.size()-1);
        assertThat(screen1.getRefreshRate() == screen.getRefreshRate());
        assertThat(screen1.getDiagonal() == screen.getDiagonal());
        assertThat(screen1.getResponseTime() == screen.getResponseTime());
        screenService.deleteById(screen1.getId());
    }

    @Test
    public void screenServiceDeleteWorksCorrectly() throws EmptyResultDataAccessException {
        screenService.add(screen);
        List<Screen> allScreens = screenService.get();
        Screen screen1 = allScreens.get(allScreens.size()-1);
        screenService.deleteById(screen1.getId());
        Throwable exception = assertThrows(EmptyResultDataAccessException.class, ()->{
            screenService.deleteById(screen1.getId());
        });
    }

    @Test
    public void screenServiceFindByIdWorksCorrectly(){
        screenService.add(screen);
        List<Screen> allScreens = screenService.get();
        Screen screen1 = screenService.findById(allScreens.get(allScreens.size()-1).getId()).get();
        assertThat(screen1.getRefreshRate() == screen.getRefreshRate());
        assertThat(screen1.getDiagonal() == screen.getDiagonal());
        assertThat(screen1.getResponseTime() == screen.getResponseTime());
        screenService.deleteById(screen1.getId());
    }
}
