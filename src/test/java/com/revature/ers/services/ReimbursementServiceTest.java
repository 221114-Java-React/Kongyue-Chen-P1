package com.revature.ers.services;

import com.revature.ers.daos.ReimbursementDAO;
import com.revature.ers.dtos.requests.NewReimbursementRequest;
import com.revature.ers.models.Reimbursement;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

public class ReimbursementServiceTest {
    private ReimbursementService sut;
    private final ReimbursementDAO mockReimbursementDAO = Mockito.mock(ReimbursementDAO.class);

    @Before
    public void init() {
        sut = new ReimbursementService(mockReimbursementDAO);
    }

    @Test
    public void test_getAllReimbs_givenObj() {
        // Arrange
        Reimbursement stubbed1 = new Reimbursement();
        Reimbursement stubbed2 = new Reimbursement();
        Reimbursement stubbed3 = new Reimbursement();
        List<Reimbursement> stubbedList = Arrays.asList(stubbed1, stubbed2, stubbed3);
        Mockito.when(mockReimbursementDAO.findAll()).thenReturn(stubbedList);

        // Act
        List<Reimbursement> reimbursements = sut.getAllReimbursements();
        boolean condition = reimbursements.isEmpty();

        // Assert
        assertFalse(condition);
    }

    @Test
    public void test_submit_persistReimbGivenValidParameters() {
        // Arrange
        ReimbursementService spySut = Mockito.spy(sut);
        String id = "122";
        double validAmount = 100.00;
        String description = "gas money";
        String typeId = "1";

        NewReimbursementRequest stubbedReq = new NewReimbursementRequest(validAmount, description, "1");

        // Act
        Reimbursement createdReimbursement = spySut.submitReimbursement(stubbedReq, id);

        // Assert
        assertNotNull(createdReimbursement);
        assertNotNull(createdReimbursement.getId());
        assertEquals(validAmount, createdReimbursement.getAmount(), 0.1);

        assertNotNull(createdReimbursement.getDescription());
        assertEquals(description, createdReimbursement.getDescription());

        assertEquals(id, createdReimbursement.getAuthor_id());
        assertNotNull(createdReimbursement.getType_id());
        assertEquals("1", createdReimbursement.getType_id());
        Mockito.verify(mockReimbursementDAO, Mockito.times(1)).save(createdReimbursement);
    }

    @Test
    public void test_getAllReimbsByAuthorId_givenValidAuthorId() {
        // Arrange
        String validAuthorId = "122222233333";
        Reimbursement stubbed1 = new Reimbursement();
        Reimbursement stubbed2 = new Reimbursement();
        Reimbursement stubbed3 = new Reimbursement();
        List<Reimbursement> stubbedList = Arrays.asList(stubbed1, stubbed2, stubbed3);
        Mockito.when(mockReimbursementDAO.getAllReimbursementsByUserId(validAuthorId)).thenReturn(stubbedList);

        // Act
        List<Reimbursement> cList = sut.getAllReimbursementsByUserId(validAuthorId);
        boolean condition = cList.isEmpty();

        // Assert
        assertFalse(condition);
    }


    @Test
    public void test_getAllReimbsByStatusId_givenValidStatusId() {
        // Arrange
        String validStatusId = "1";
        Reimbursement stubbed1 = new Reimbursement();
        Reimbursement stubbed2 = new Reimbursement();
        Reimbursement stubbed3 = new Reimbursement();
        List<Reimbursement> stubbedList = Arrays.asList(stubbed1, stubbed2, stubbed3);
        Mockito.when(mockReimbursementDAO.finaAllByStatus(validStatusId)).thenReturn(stubbedList);

        // Act
        List<Reimbursement> cList = sut.getAllReimbursementsByStatus(validStatusId);
        boolean condition = cList.isEmpty();

        // Assert
        assertFalse(condition);
    }



}