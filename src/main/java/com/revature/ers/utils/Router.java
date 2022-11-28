package com.revature.ers.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.ers.daos.UserDAO;
import com.revature.ers.services.UserService;
import com.revature.ers.handlers.UserHandler;
import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;

public class Router {
    public static void router(Javalin app) {
        ObjectMapper mapper = new ObjectMapper();
        UserDAO userDao = new UserDAO();
        UserService userService = new UserService(userDao);
        UserHandler userHandler = new UserHandler(userService, mapper);

        app.routes(()-> {
            path("/users", () -> {
                post(c -> userHandler.signup(c) );
            });
        });
    }
}
