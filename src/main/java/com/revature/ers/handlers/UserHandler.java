package com.revature.ers.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.ers.dtos.requests.NewUserRequest;
import com.revature.ers.dtos.responses.Principal;
import com.revature.ers.models.User;
import com.revature.ers.services.TokenService;
import com.revature.ers.services.UserService;
import com.revature.ers.utils.custom_exceptions.InvalidAuthException;
import com.revature.ers.utils.custom_exceptions.InvalidUserException;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;


// Handles http verbs and endpoints
// userHandler depends on userServers who depends on userDao || userHandler -> userServices -> userDao
public class UserHandler {
    private final TokenService tokenService;
    private final UserService userService;
    private final ObjectMapper mapper;

    private final static Logger logger = LoggerFactory.getLogger(User.class);

    public UserHandler(UserService userService,TokenService tokenService,  ObjectMapper mapper) {
        this.tokenService = tokenService;
        this.userService = userService;
        this.mapper = mapper;
    }

    public void signup(Context ctx) throws IOException {
        NewUserRequest req = mapper.readValue(ctx.req.getInputStream(), NewUserRequest.class);
        try {
            logger.info("Attempting to signup...");

            User createdUser;

            if (userService.isValidUsername(req.getUsername())) {
                if (!userService.isDuplicateUsername(req.getUsername())) {
                    if (userService.isValidPassword(req.getPassword1())) {
                        if (userService.isSamePassword(req.getPassword1(), req.getPassword2())) {
                            if(!userService.isDuplicateEmail(req.getEmail())) {
                                createdUser = userService.signup(req);
                            } else throw new InvalidUserException("Email already in use");
                        } else throw new InvalidUserException("Passwords doe not match");
                    } else throw new InvalidUserException("Password needs to be minimum 8 characters long, and one number");
                } else throw new InvalidUserException("Username is already taken");
            } else throw new InvalidUserException("Username needs to be 8 - 20 characters long");

            ctx.status(201); // CREATED
            ctx.json(createdUser.getId());
            logger.info("Signup attempt successful...");
        } catch (InvalidUserException e) {
            ctx.status(403); // FORBIDDEN
            ctx.json(e);
            logger.info("Signup attempt unsuccessful...");
        }
    }


    public void getAllUsers(Context ctx) {

        try{
            String token = ctx.req.getHeader("authorization");
            if(token == null || token.isEmpty()) throw new InvalidAuthException("You are not signed in!");
            Principal principal = tokenService.extractRequestersDetails(token);
            if(principal == null) throw new InvalidAuthException("Invalid token");
            if(!principal.getRoleId().equals("2")) throw new InvalidAuthException("You are not authorized");
            logger.info("Principal: "+principal.toString());
            List<User> users = userService.getAllUsers();
            ctx.json(users);
        } catch (InvalidAuthException e) {
            ctx.status(401);
            ctx.json(e);
        }
    }

    public void getAllUsersByUsername(Context ctx) {

        try{
            String token = ctx.req.getHeader("authorization");
            if(token == null || token.isEmpty()) throw new InvalidAuthException("You are not signed in!");
            Principal principal = tokenService.extractRequestersDetails(token);
            if(principal == null) throw new InvalidAuthException("Invalid token");
            if(!principal.getRoleId().equals("2")) throw new InvalidAuthException("You are not authorized");

            String username = ctx.req.getParameter("username");
            List<User> users = userService.getAllUsersByUsername(username);
            ctx.json(users);

        } catch (InvalidAuthException e) {
            ctx.status(401);
            ctx.json(e);
        }

    }
}
