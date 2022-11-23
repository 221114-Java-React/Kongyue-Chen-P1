package com.revature.ers.services;


import com.revature.ers.daos.UserDao;

/* Validate and retrieve from DAO: Data Access Object
*  Services are api kind of
*
* */
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }
}
