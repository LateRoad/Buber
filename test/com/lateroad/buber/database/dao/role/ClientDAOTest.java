package com.lateroad.buber.database.dao.role;

import com.lateroad.buber.database.dao.UserDAO;
import com.lateroad.buber.entity.role.Client;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ClientDAOTest {
    private ClientDAO clientDAO = ClientDAO.getInstance();
    private UserDAO userDAO = UserDAO.getInstance();
    private CommonUserDAO commonUserDAO = CommonUserDAO.getInstance();

    private Client testClient = new Client(
            "test",
            "name",
            "surname",
            "lastname",
            "email",
            "phone");


    @BeforeClass
    public void setUp() throws Exception {
        commonUserDAO.insert(testClient.getLogin(), "password", testClient.getRole());
        userDAO.insert(testClient.getLogin(), testClient);
        clientDAO.insert(testClient.getLogin(), testClient);
    }

    @AfterClass
    public void dropDown() throws Exception {
        userDAO.delete(testClient.getLogin());
    }


    @Test
    public void testFindByLogin() throws Exception {
        Client actualClient = clientDAO.find(testClient.getLogin());
        Assert.assertEquals(actualClient, testClient);
    }

    @Test
    public void testFindByLoginAndPassword() throws Exception {
        Client actualClient = clientDAO.find(testClient.getLogin(), "password");
        Assert.assertEquals(actualClient, testClient);
    }

    @Test
    public void testFindAll() throws Exception {
        clientDAO.findAll();
    }

    @Test
    public void testUpdateInfo() throws Exception {
        testClient.setPhoneNumber("wrong");
        clientDAO.update(testClient.getLogin(), testClient);
        Client actualClient = clientDAO.find(testClient.getLogin());
        Assert.assertEquals(actualClient, testClient);
    }

    @Test
    public void testUpdateStatus() throws Exception {
        clientDAO.update(testClient.getLogin(), true);
    }
}