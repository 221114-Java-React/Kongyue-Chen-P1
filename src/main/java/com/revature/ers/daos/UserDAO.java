package com.revature.ers.daos;

import com.revature.ers.models.User;
import com.revature.ers.utils.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements CrudDAO<User> {

    @Override
    public void save(User obj) {
        try(Connection con = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement ps = con.prepareStatement("INSERT INTO users (id, username, email, pwd, given_name, surname, is_active, role_id) VALUES (?,?,?,?,?,?,?,?)");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getUsername());
            ps.setString(3, obj.getEmail());
            ps.setString(4, obj.getPwd());
            ps.setString(5, obj.getGivenName());
            ps.setString(6, obj.getSurname());
            ps.setBoolean(7, obj.getActive());
            ps.setString(8, obj.getRoleId());
            ps.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User obj) {

    }

    @Override
    public void update(User obj) {

    }

    @Override
    public User findById() {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    /* Custom Methods */
    public List<String> findAllUserNames() {
        List<String> usernames = new ArrayList<>();
        try(Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT (username) from users");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                String currentUsername = rs.getString("username");
                usernames.add(currentUsername);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return usernames;
    }
}
