package com.project.sap.services.interfaces;

import com.project.sap.models.*;

import java.util.List;

public interface AddComponentsService{
    List<RAM> getRams();
    List<Screen> getScreens();
    List<Processor> getProcessors();
    List<VideoCard> getVideoCards();
    List<Storage> getStorages();
}
