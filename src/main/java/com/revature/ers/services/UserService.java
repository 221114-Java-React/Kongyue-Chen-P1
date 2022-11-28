package com.revature.ers.services;


import com.revature.ers.daos.UserDAO;
import com.revature.ers.dtos.requests.NewUserRequest;
import com.revature.ers.models.User;
import com.revature.ers.utils.custom_exceptions.InvalidUserException;

import java.util.List;
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

    /*
    Uses the NewUserRequest object to fill out the user constructor
    Also assigns out the default ID, ACTIVE, ROLE
    */

    public void saveUser(NewUserRequest req) {
        List<String> usernames = userDao.findAllUserNames();
        if(!req.getPassword1().equals(req.getPassword2())) throw new InvalidUserException("Password do not match.");
        if(usernames.contains(req.getUsername())) throw new InvalidUserException("Username is already taken");


        User createdUser = new User(
                UUID.randomUUID().toString(),
                req.getUsername(),
                req.getEmail(),
                req.getPassword1(),
                req.getGivenName(),
                req.getSurname(),
                true,
                "DEFAULT"
        );
        userDao.save(createdUser);
    }


    //Validation Methods
}
