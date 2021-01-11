package com.project.sap.services;

import com.project.sap.models.User;
import com.project.sap.models.Dto.UserDto;
import com.project.sap.repositories.UserRepository;
import com.project.sap.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> get() {
        return userRepository.findAll();
    }

    @Override
    public void deleteById(long id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public User getUserById(long id) {
        return this.userRepository.findById(id).get();
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findAll().stream().filter(x->x.getEmail().equals(email)).findFirst().get();
    }

    @Override
    public boolean hasSales(long id){
        return this.getUserById(id).getSales().size()>0;
    }
}
