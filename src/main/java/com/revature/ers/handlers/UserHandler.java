package com.revature.ers.handlers;

import com.revature.ers.models.User;
import com.revature.ers.services.UserService;
import io.javalin.http.Context;

public class UserHandler {
    private final UserService userService;

    public UserHandler(UserService userService){
        this.userService = userService;
    }
    public void signup(Context c) {

    }
}
