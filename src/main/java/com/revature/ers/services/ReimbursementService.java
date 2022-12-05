package com.revature.ers.services;

import com.revature.ers.daos.ReimbursementDAO;
import com.revature.ers.dtos.requests.NewReimUpdateRequest;
import com.revature.ers.dtos.requests.NewReimbursementRequest;
import com.revature.ers.models.Reimbursement;
import com.revature.ers.utils.custom_exceptions.InvalidReimException;
import com.revature.ers.utils.custom_exceptions.InvalidStatusException;

import java.util.List;
import java.util.UUID;


public class ReimbursementService {
    private final ReimbursementDAO reimbursementDAO;

    public ReimbursementService(ReimbursementDAO reimbursementDAO) {
        this.reimbursementDAO = reimbursementDAO;
    }

    public void submitReimbursement(NewReimbursementRequest req, String id) {
        if(req.getAmount() < 0.01) throw new InvalidReimException("There must be a amount");
        if(req.getDescription().isEmpty()) throw new InvalidReimException("There must be a description");

        long millis=System.currentTimeMillis();
        java.sql.Time time = new java.sql.Time(millis);

        Reimbursement createdReimbursement = new Reimbursement(
                UUID.randomUUID().toString(),
                req.getAmount(),
                time,
                null,
                req.getDescription(),
                null,
                id,
                null,
                "1",
                req.getType_id()
        );
        reimbursementDAO.save(createdReimbursement);
    }

    public List<Reimbursement> getAllReimbursements() {
        return reimbursementDAO.findAll();
    }

    public List<Reimbursement> getAllReimbursementsByUserId(String id) {
        return reimbursementDAO.getAllReimbursementsByUserId(id);
    }

    public List<Reimbursement> getAllReimUserAndStatus(String id, String status) {
        return reimbursementDAO.getAllReimUserAndStatus(id, status);
    }

    public void updateReimbursement(NewReimUpdateRequest req, String resolverId) {
        Reimbursement requested = reimbursementDAO.findById(req.getId());
        if(!(requested.getStatus_id().equals("1"))) throw new InvalidStatusException("Cannot update resolved reimbursement");

        reimbursementDAO.updateReimbursement(req, resolverId);
    }

    public List<Reimbursement> getAllReimbursementsByStatus(String status) {
        return reimbursementDAO.finaAllByStatus(status);
    }
}
