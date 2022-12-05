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
    public User findById(String id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();

        try(Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * from users WHERE role_id='1'");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                User currentUser = new User(
                        rs.getString("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("pwd"),
                        rs.getString("given_name"),
                        rs.getString("surname"),
                        rs.getBoolean("is_active"),
                        rs.getString("role_id")
                );
                users.add(currentUser);
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return users;
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

    public List<String> findAllEmails() {
        List<String> emails = new ArrayList<>();
        try(Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT (email) from users");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                String currentEmail = rs.getString("email");
                emails.add(currentEmail);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return emails;
    }

    public User getUserByUsernameAndPassword(String username, String password) {
        User user = null;
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE username = ? AND pwd = ?;");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                user = new User(
                        rs.getString("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("pwd"),
                        rs.getString("given_name"),
                        rs.getString("surname"),
                        rs.getBoolean("is_active"),
                        rs.getString("role_id")

                        );
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<User> getAllUsersByUsername(String username) {
        List<User> users = new ArrayList<>();
        try(Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE username LIKE ?");
            ps.setString(1, username + "%");
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                User user = new User(
                        rs.getString("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("pwd"),
                        rs.getString("given_name"),
                        rs.getString("surname"),
                        rs.getBoolean("is_active"),
                        rs.getString("role_id")
                );
                users.add(user);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return users;
    }
}
