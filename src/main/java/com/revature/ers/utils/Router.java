package com.revature.ers.utils;

import com.revature.ers.daos.UserDao;
import com.revature.ers.services.UserService;
import com.revature.ers.handlers.UserHandler;
import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;

public class Router {
    public static void router(Javalin app) {
        UserDao userDao = new UserDao();
        UserService userService = new UserService(userDao);
        UserHandler userHandler = new UserHandler(userService);


        app.routes(()-> {
            path("/users", () -> {
                post(c -> userHandler.signup(c) );
            });
        });
    }
}
