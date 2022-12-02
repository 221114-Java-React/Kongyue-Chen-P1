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
    public void saveUser(NewUserRequest req) {
        List<String> usernames = userDao.findAllUserNames();

        if(!usernameValidation(req.getUsername())) throw new InvalidUserException("Username needs to be 8-20 Characters");
        if(usernames.contains(req.getUsername())) throw new InvalidUserException("Username is already taken");

        if(!passwordValidation(req.getPassword1())) throw new InvalidUserException("Passwords need to contain minimum of 8 characters");
        if(!req.getPassword1().equals(req.getPassword2())) throw new InvalidUserException("Password do not match.");



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
    }

    public Principal login(NewLoginRequest req) {
        User validUser = userDao.getUserByUsernameAndPassword(req.getUsername(), req.getPassword());
        if(validUser == null) throw new InvalidAuthException("Invalid user or password");
        return new Principal(validUser.getId(), validUser.getUsername(), validUser.getRoleId());
    }

    public List<User> getAllUsers() {
        return userDao.findAll();
    }


    //Validation Methods
    private boolean usernameValidation(String username) {
        return username.matches("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$");
    }

    private boolean passwordValidation(String password) {
        return password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
    }
}
