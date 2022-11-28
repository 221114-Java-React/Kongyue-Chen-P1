package com.revature.ers.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.ers.dtos.requests.NewUserRequest;
import com.revature.ers.services.UserService;
import com.revature.ers.utils.custom_exceptions.InvalidUserException;
import io.javalin.http.Context;

import java.io.IOException;


// Handles http verbs and endpoints
// userHandler depends on userServers who depends on userDao || userHandler -> userServices -> userDao
public class UserHandler {
    private final UserService userService;
    private final ObjectMapper mapper;

    public UserHandler(UserService userService, ObjectMapper mapper){
        this.userService = userService;
        this.mapper = mapper;
    }

    public void signup(Context c) throws IOException {
        NewUserRequest req = mapper.readValue(c.req.getInputStream(), NewUserRequest.class);
        try{
            userService.saveUser(req);
            c.status(201);
        }catch (InvalidUserException e) {
            c.status(403);
            c.json(e);
        }
    }
}
