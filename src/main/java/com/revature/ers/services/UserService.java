package com.revature.ers.services;


import com.revature.ers.daos.UserDAO;
import com.revature.ers.dtos.requests.NewLoginRequest;
import com.revature.ers.dtos.requests.NewUserRequest;
import com.revature.ers.dtos.responses.Principal;
import com.revature.ers.models.User;
import com.revature.ers.utils.custom_exceptions.InvalidAuthException;
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
    public User signup(NewUserRequest req) {

        User createdUser = new User(
                UUID.randomUUID().toString(),
                req.getUsername(),
                req.getEmail(),
                req.getPassword1(),
                req.getGivenName(),
                req.getSurname(),
                true,
                "1"
        );

        userDao.save(createdUser);
        return createdUser;
    }

    public Principal login(NewLoginRequest req) {
        User validUser = userDao.getUserByUsernameAndPassword(req.getUsername(), req.getPassword());
        if(validUser == null) throw new InvalidAuthException("Invalid user or password");
        return new Principal(validUser.getId(), validUser.getUsername(), validUser.getRoleId());
    }

    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    public List<User> getAllUsersByUsername(String username) {
        return userDao.getAllUsersByUsername(username);
    }


    public boolean isValidUsername(String username) {
        return username.matches("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$");
    }
    public boolean isDuplicateUsername(String username) {
        List<String> usernames = userDao.findAllUserNames();
        return usernames.contains(username);
    }

    public boolean isDuplicateEmail(String email) {
        List<String> emails = userDao.findAllEmails();
        return emails.contains(email);
    }

    public boolean isValidPassword(String password) {
        return password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
    }

    public boolean isSamePassword(String password1, String password2) {
        return password1.equals(password2);
    }
}
