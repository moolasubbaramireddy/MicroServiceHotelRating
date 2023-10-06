package com.userService.service.impl;

import com.userService.entity.User;
import com.userService.repository.UserRepository;
import com.userService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User saveUser(User user) {
        //for ganarating random userID
        String s = UUID.randomUUID().toString();
        user.setUserId(s);
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        return userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User is not found with :"+userId));
    }

    @Override
    public User updateUser(String userId, User updateUser) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User id is not avaliable check it once your database"));
        user.setName(updateUser.getName());
        user.setEmail(updateUser.getEmail());
        user.setAbout(updateUser.getAbout());
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(String userId) {
    if (userRepository.existsById(userId)){
        userRepository.deleteById(userId);
    }else {
        throw new RuntimeException("User not found with ID: " + userId);
    }
    }
}
