package com.revature.ers.services;

import com.revature.ers.daos.UserDAO;
import com.revature.ers.dtos.requests.NewUserRequest;
import com.revature.ers.models.User;
import org.junit.Before;
import org.junit.Test;

import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
public class UserServiceTest {

    private UserService sut;
    private final UserDAO mockUserDao = Mockito.mock(UserDAO.class);
    @Before
    public void init() {
        sut = new UserService(mockUserDao);

    }
    @Test
    public void test_isValidUsername_givenCorrectUsername(){
        // Arrange
        String validUsername = "tester011";

        //Act
        boolean condition = sut.isValidUsername(validUsername);

        // Assert
        assertTrue(condition);
    }

    @Test
    public void test_isValidPassword_givenValidPassword() {
        // Arrange
        String validPassword = "therewasapass1";

        // Act
        boolean condition = sut.isValidPassword(validPassword);

        // Assert
        assertTrue(condition);
    }

    @Test
    public void test_isSamePassword_givenSamePassword() {
        // Arrange
        String password = "password555";
        String samePassword = "password555";

        // Act
        boolean condition = sut.isSamePassword(password, samePassword);

        // Assert
        assertTrue(condition);
    }

    @Test
    public void test_isValidUsername_givenUniqueUsername() {
        // Arrange
        UserService spySut = Mockito.spy(sut);
        String uniqueUsername = "tester111";
        List<String> stubbedUsernames = Arrays.asList("tester001", "tester002", "tester003");

        // controlled env
        Mockito.when(mockUserDao.findAllUserNames()).thenReturn(stubbedUsernames);

        // Act
        boolean condition = spySut.isDuplicateUsername(uniqueUsername);

        // Assert
        assertFalse(condition);
    }

    @Test
    public void test_isDuplicateEmail_givenDuplicateEmail() {
        // Arrange
        UserService spySut = Mockito.spy(sut);
        String duplicateEmail = "tester111@revature.com";
        List<String> stubbedEmails = Arrays.asList("tester1@revature.com", "tester2@revature.com", "tester3@revature.com");

        Mockito.when(mockUserDao.findAllEmails()).thenReturn(stubbedEmails);

        // Act
        boolean condition = spySut.isDuplicateEmail(duplicateEmail);

        // Assert
        assertFalse(condition);
    }

    @Test
    public void test_isValidSignup_persistUserGivenUsernameAndPassword() {
        // Arrange
        UserService spySut = Mockito.spy(sut);
        String validUsername = "tester1221";
        String validEmail = "tester1221@revature.net";
        String validPassword1 = "passw0rd12";
        String validPassword2 = "passw0rd12";
        String validGivenname = "yeahyeahyeah";
        String validSurname = "nonono";
        NewUserRequest stubbedReq = new NewUserRequest(validUsername, validEmail, validPassword1, validPassword2, validGivenname, validSurname);

        // Act
        User createdUser = spySut.signup(stubbedReq);

        // Assert
        assertNotNull(createdUser);
        assertNotNull(createdUser.getId());
        assertNotNull(createdUser.getUsername());
        assertNotNull(createdUser.getPwd());
        assertNotNull(createdUser.getGivenName());
        assertNotNull(createdUser.getSurname());
        assertNotEquals("", createdUser.getUsername());
        assertEquals("1", createdUser.getRoleId());
        assertEquals(validGivenname, createdUser.getGivenName());
        Mockito.verify(mockUserDao, Mockito.times(1)).save(createdUser);
    }
    @Test
    public void test_getAllUsers_givenUsers() {
        // Arrange
        User stubbedUser1 = new User();
        User stubbedUser2 = new User();
        User stubbedUser3 = new User();
        List <User> stubbedUsers = Arrays.asList(stubbedUser1, stubbedUser2, stubbedUser3);
        Mockito.when(mockUserDao.findAll()).thenReturn(stubbedUsers);

        // Act
        List<User> list = sut.getAllUsers();
        boolean condition = list.isEmpty();

        // Assert
        assertFalse(condition);
    }

}