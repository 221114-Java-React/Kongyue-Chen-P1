package com.revature.ers.daos;

import com.revature.ers.models.Reimbursement;
import com.revature.ers.utils.ConnectionFactory;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ReimbursementDAO implements CrudDAO<Reimbursement> {


    @Override
    public void save(Reimbursement obj) {
        try(Connection con = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement ps = con.prepareStatement("INSERT INTO reimbursement (id, amount, submitted, resolved, description, receipt, payment_id, author_id, resolver_id, status_id, type_id) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, obj.getId());
            ps.setDouble(2, obj.getAmount());
            ps.setDate(3, (Date) obj.getSubmitted());
            ps.setDate(4, (Date) obj.getResolved());
            ps.setString(5, obj.getDescription());
            ps.setBlob(6, obj.getReceipt());
            ps.setString(7, obj.getPayment_id());
            ps.setString(8, obj.getAuthor_id());
            ps.setString(9, obj.getResolver_id());
            ps.setString(10, obj.getStatus_id());
            ps.setString(11, obj.getType_id());

            ps.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Reimbursement obj) {

    }

    @Override
    public void update(Reimbursement obj) {

    }

    @Override
    public Reimbursement findById() {
        return null;
    }

    @Override
    public List<Reimbursement> findAll() {
        return null;
    }
}
