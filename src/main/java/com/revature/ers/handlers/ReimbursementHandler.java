package com.revature.ers.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.ers.models.Reimbursement;
import com.revature.ers.models.User;
import com.revature.ers.services.TokenService;
import com.revature.ers.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReimbursementHandler {
    private final UserService userService;
    private final TokenService tokenService;
    private final ObjectMapper mapper;

    private final static Logger logger = LoggerFactory.getLogger(Reimbursement.class);
    public Reimbursement submitReimbursement;

    public ReimbursementHandler(UserService userService, TokenService tokenService, ObjectMapper mapper) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.mapper = mapper;
    }
}
