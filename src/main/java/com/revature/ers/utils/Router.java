package com.revature.ers.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.ers.daos.ReimbursementDAO;
import com.revature.ers.daos.UserDAO;
import com.revature.ers.handlers.AuthHandler;
import com.revature.ers.handlers.ReimbursementHandler;
import com.revature.ers.services.ReimbursementService;
import com.revature.ers.services.TokenService;
import com.revature.ers.services.UserService;
import com.revature.ers.handlers.UserHandler;
import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Router {
    public static void router(Javalin app) {

        ObjectMapper mapper = new ObjectMapper();
        JwtConfig jwtConfig = new JwtConfig();
        TokenService tokenService = new TokenService(jwtConfig);

        //User
        UserDAO userDao = new UserDAO();
        UserService userService = new UserService(userDao);
        UserHandler userHandler = new UserHandler(userService, tokenService, mapper);
        //Auth
        AuthHandler  authHandler = new AuthHandler(userService, tokenService, mapper);
        //Reimbursement
        ReimbursementDAO reimbursementDAO = new ReimbursementDAO();
        ReimbursementService reimbursementService= new ReimbursementService(reimbursementDAO);
        ReimbursementHandler reimbursementHandler = new ReimbursementHandler(reimbursementService, tokenService, mapper);

        app.routes(()-> {
            path("/users", () -> {
                get(userHandler::getAllUsers);
                get("/name", userHandler::getAllUsersByUsername);
                post(c -> userHandler.signup(c) );
            });

            path("/auth", () -> {
                post(c -> authHandler.authenticateUser(c) );
            });

            path("/Reimbursement", () -> {
                post(c -> reimbursementHandler.submitReimbursement(c));
            });
        });
    }
}
