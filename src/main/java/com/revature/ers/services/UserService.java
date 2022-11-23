package com.revature.ers.services;


import com.revature.ers.daos.UserDAO;
import com.revature.ers.dtos.requests.NewUserRequest;
import com.revature.ers.models.User;

import java.util.UUID;

/* Validate and retrieve from DAO: Data Access Object
*  Services are api kind of
*
* */
public class UserService {
    private final UserDAO userDao;

    public UserService(UserDAO userDao) {
        this.userDao = userDao;
    }

    public void saveUser(NewUserRequest req) {
        User createdUser = new User(UUID.randomUUID().toString(), req.getUsername(), req.getEmail(), req.getPassword1());
    }
}
