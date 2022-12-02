package com.revature.ers.services;

import com.revature.ers.daos.ReimbursementDAO;
import com.revature.ers.dtos.requests.NewReimUpdateRequest;
import com.revature.ers.dtos.requests.NewReimbursementRequest;
import com.revature.ers.models.Reimbursement;
import com.revature.ers.utils.custom_exceptions.InvalidReimException;

import java.util.Date;
import java.util.List;
import java.util.UUID;


public class ReimbursementService {
    private final ReimbursementDAO reimbursementDAO;

    public ReimbursementService(ReimbursementDAO reimbursementDAO) {
        this.reimbursementDAO = reimbursementDAO;
    }

    public void submitReimbursement(NewReimbursementRequest req, String id) {
        if(req.getAmount() >= 0.00) throw new InvalidReimException("There must be a amount");
        if(req.getDescription().isEmpty()) throw new InvalidReimException("There must be a description");

        Reimbursement createdReimbursement = new Reimbursement(
                UUID.randomUUID().toString(),
                req.getAmount(),
                new Date(),
                null,
                req.getDescription(),
                null,
                null,
                id,
                "33db0fb2-b953-4cd0-8877-5c875de12cf7",
                "1",
                req.getType_id()
        );
        reimbursementDAO.save(createdReimbursement);
    }

    public List<Reimbursement> getAllReimbursements() {
        return reimbursementDAO.findAll();
    }

    public void updateReimbursement(NewReimUpdateRequest req) {
        reimbursementDAO.updateReinbursement(req);
    }
}
