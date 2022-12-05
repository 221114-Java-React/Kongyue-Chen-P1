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
            PreparedStatement ps = con.prepareStatement("INSERT INTO reimbursements (id, amount, submitted, resolved, description, payment_id, author_id, resolver_id, status_id, type_id) VALUES (?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, obj.getId());
            ps.setDouble(2, obj.getAmount());
            ps.setTime(3, obj.getSubmitted());
            ps.setTime(4, obj.getResolved());
            ps.setString(5, obj.getDescription());
            ps.setString(6, obj.getPayment_id());
            ps.setString(7, obj.getAuthor_id());
            ps.setString(8, obj.getResolver_id());
            ps.setString(9, obj.getStatus_id());
            ps.setString(10, obj.getType_id());

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
    public Reimbursement findById(String id) {
        Reimbursement reimbursement = new Reimbursement();
        try(Connection con = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT * FROM reimbursements WHERE id = ?");
            ps.setString(1,id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                reimbursement = new Reimbursement(
                        rs.getString("id"),
                        rs.getDouble("amount"),
                        rs.getTime("submitted"),
                        rs.getTime("resolved"),
                        rs.getString("description"),
                        rs.getString("payment_id"),
                        rs.getString("author_id"),
                        rs.getString("resolver_id"),
                        rs.getString("status_id"),
                        rs.getString("type_id")

                );
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return reimbursement;
    }

    @Override
    public List<Reimbursement> findAll() {
        List<Reimbursement> reimbursements = new ArrayList<>();

        try(Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM reimbursements");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Reimbursement current = new Reimbursement(
                        rs.getString("id"),
                        rs.getDouble("amount"),
                        rs.getTime("submitted"),
                        rs.getTime("resolved"),
                        rs.getString("description"),
                        rs.getString("payment_id"),
                        rs.getString("author_id"),
                        rs.getString("resolver_id"),
                        rs.getString("status_id"),
                        rs.getString("type_id")

                        );
                reimbursements.add(current);
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }

        return reimbursements;
    }

    public List<Reimbursement> finaAllByStatus(String status) {
        List<Reimbursement> reimbursements = new ArrayList<>();

        try(Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM reimbursements WHERE status_id =?");
            ps.setString(1, status);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Reimbursement current = new Reimbursement(
                        rs.getString("id"),
                        rs.getDouble("amount"),
                        rs.getTime("submitted"),
                        rs.getTime("resolved"),
                        rs.getString("description"),
                        rs.getString("payment_id"),
                        rs.getString("author_id"),
                        rs.getString("resolver_id"),
                        rs.getString("status_id"),
                        rs.getString("type_id")

                );
                reimbursements.add(current);
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }

        return reimbursements;
    }

    public void updateReimbursement(NewReimUpdateRequest req, String resolverId) {
        try(Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE reimbursements SET resolved = ?, status_id = ?, resolver_id = ?  WHERE id = ?;");
            long millis=System.currentTimeMillis();
            java.sql.Time time = new Time(millis);
            ps.setTime(1,time );
            ps.setString(2, req.getStatus_id());
            ps.setString(3, resolverId);
            ps.setString(4,req.getId());
            ps.executeUpdate();
            ps.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Reimbursement> getAllReimbursementsByUserId(String id) {
        List<Reimbursement> reimbursements = new ArrayList<>();
        try(Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM reimbursements WHERE author_id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Reimbursement reimbursement = new Reimbursement(
                        rs.getString("id"),
                        rs.getDouble("amount"),
                        rs.getTime("submitted"),
                        rs.getTime("resolved"),
                        rs.getString("description"),
                        rs.getString("payment_id"),
                        rs.getString("author_id"),
                        rs.getString("resolver_id"),
                        rs.getString("status_id"),
                        rs.getString("type_id")

                );
                reimbursements.add(reimbursement);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return reimbursements;
    }

    public List<Reimbursement> getAllReimUserAndStatus(String id, String status) {
        List<Reimbursement> reimbursements = new ArrayList<>();
        try(Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM reimbursements WHERE author_id =? AND status_id=?");
            ps.setString(1, id);
            ps.setString(2, status);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Reimbursement reimbursement = new Reimbursement(
                        rs.getString("id"),
                        rs.getDouble("amount"),
                        rs.getTime("submitted"),
                        rs.getTime("resolved"),
                        rs.getString("description"),
                        rs.getString("payment_id"),
                        rs.getString("author_id"),
                        rs.getString("resolver_id"),
                        rs.getString("status_id"),
                        rs.getString("type_id")

                );
                reimbursements.add(reimbursement);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return reimbursements;
    }



}
