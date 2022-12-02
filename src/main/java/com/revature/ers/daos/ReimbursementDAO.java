package com.revature.ers.daos;

import com.revature.ers.dtos.requests.NewReimUpdateRequest;
import com.revature.ers.models.Reimbursement;
import com.revature.ers.models.User;
import com.revature.ers.utils.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
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
        List<Reimbursement> reimbursements = new ArrayList<>();

        try(Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * from reimbursement WHERE status_id='1'");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Reimbursement currentReim = new Reimbursement(
                        rs.getString("id"),
                        rs.getDouble("amount"),
                        rs.getDate("submitted"),
                        rs.getDate("resolved"),
                        rs.getString("description"),
                        rs.getBlob("receipt"),
                        rs.getString("payment_id"),
                        rs.getString("author_id"),
                        rs.getString("resolver_id"),
                        rs.getString("status_id"),
                        rs.getString("type_id")

                        );
                reimbursements.add(currentReim);
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return reimbursements;
    }

    public void updateReinbursement(NewReimUpdateRequest req) {
        try(Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE reimbursements SET resolved = ?, status_id = ? WHERE id = ?;");
            Date tDate = (Date) new java.util.Date();
            ps.setDate(1, tDate );
            ps.setString(2, req.getStatus_id());
            ps.executeUpdate();
            ps.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
