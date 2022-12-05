package com.revature.ers.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.ers.dtos.requests.NewReimUpdateRequest;
import com.revature.ers.dtos.requests.NewReimbursementRequest;
import com.revature.ers.dtos.responses.Principal;
import com.revature.ers.models.Reimbursement;
import com.revature.ers.services.ReimbursementService;
import com.revature.ers.services.TokenService;
import com.revature.ers.utils.custom_exceptions.InvalidAuthException;
import com.revature.ers.utils.custom_exceptions.InvalidStatusException;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class ReimbursementHandler {
    private final ReimbursementService reimbursementService;
    private final TokenService tokenService;
    private final ObjectMapper mapper;
    private final static Logger logger = LoggerFactory.getLogger(Reimbursement.class);

    public ReimbursementHandler(ReimbursementService reimbursementService, TokenService tokenService, ObjectMapper mapper) {
        this.reimbursementService = reimbursementService;
        this.tokenService = tokenService;
        this.mapper = mapper;
    }

    public void submitReimbursement(Context ctx) throws IOException {
        NewReimbursementRequest req = mapper.readValue(ctx.req.getInputStream(), NewReimbursementRequest.class);
        try {
            String token = ctx.req.getHeader("authorization");
            if (token == null || token.isEmpty()) throw new InvalidAuthException("You are not signed in!");
            Principal principal = tokenService.extractRequestersDetails(token);
            if (principal == null) throw new InvalidAuthException("Invalid token");
            logger.info("Principal: " + principal.toString());

            reimbursementService.submitReimbursement(req, principal.getId());
            ctx.status(201);
        } catch (InvalidAuthException e) {
            ctx.status(401);
            ctx.json(e);
        }
    }

    public void updateReimbursement(Context ctx) throws IOException {
        NewReimUpdateRequest req = mapper.readValue(ctx.req.getInputStream(), NewReimUpdateRequest.class);
        try {
            String token = ctx.req.getHeader("authorization");
            if (token == null || token.isEmpty()) throw new InvalidAuthException("You are not signed in!");
            Principal principal = tokenService.extractRequestersDetails(token);
            if (principal == null) throw new InvalidAuthException("Invalid token");
            if (!principal.getRoleId().equals("2")) throw new InvalidAuthException("You are not authorized");
            logger.info("Principal: " + principal.toString());
            reimbursementService.updateReimbursement(req, principal.getId());
        } catch (InvalidAuthException e) {
            ctx.status(401);
            ctx.json(e);
        } catch(InvalidStatusException e) {
            ctx.status(400);
            ctx.json(e);
        }
    }

    public void getAllReimbursement(Context ctx) {
        try {
            String token = ctx.req.getHeader("authorization");
            if (token == null || token.isEmpty()) throw new InvalidAuthException("You are not signed in!");
            Principal principal = tokenService.extractRequestersDetails(token);
            if (principal == null) throw new InvalidAuthException("Invalid token");
            if (!principal.getRoleId().equals("2")) throw new InvalidAuthException("You are not authorized");
            logger.info("Principal: " + principal.toString());

            List<Reimbursement> reimbursements = reimbursementService.getAllReimbursements();
            ctx.json(reimbursements);
            ctx.status(200);
        } catch (InvalidAuthException e) {
            ctx.status(401);
            ctx.json(e);
        }
    }

    public void getAllReimbursementsByUserId(Context ctx) {
        try {
            String token = ctx.req.getHeader("authorization");
            if (token == null || token.isEmpty()) throw new InvalidAuthException("You are not signed in!");
            Principal principal = tokenService.extractRequestersDetails(token);
            if (principal == null) throw new InvalidAuthException("Invalid token");
            String id = ctx.req.getParameter("id");
            if (!principal.getId().equals(id)) throw new InvalidAuthException("You are not authorized");

            List<Reimbursement> reimbursements = reimbursementService.getAllReimbursementsByUserId(id);
            ctx.json(reimbursements);
            ctx.status(200);

        } catch (InvalidAuthException e) {
            ctx.status(401);
            ctx.json(e);
        }

    }
}
