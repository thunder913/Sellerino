package com.project.sap.services.interfaces;

import com.project.sap.models.User;
import com.project.sap.models.Dto.UserDto;

import java.util.List;

public interface UserService {
    void save(User user);
    List<User> get();
    void deleteById(long id);
    User getUserById(long id);
    User getUserByEmail(String email);
    boolean hasSales(long id);
}
