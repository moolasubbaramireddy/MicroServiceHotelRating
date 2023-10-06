package com.userService.service;

import com.userService.entity.User;

import java.util.List;

public interface UserService {

    //crete user
    User saveUser(User user);

    //get All user
    List<User> getUsers();

    //get user by userid
    User getUser(String userId);

    //update User with username
    User updateUser(String userId, User user);

    //delete user by userId
    void deleteUser(String userId);
}
