package com.example.Backend.Service;

import com.example.Backend.Model.User;
import com.example.Backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User checkLogin(String name, String password) throws Exception {
        return userRepository.checkLogin(name, password);
    }
}
