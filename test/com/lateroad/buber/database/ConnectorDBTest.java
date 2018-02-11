package com.lateroad.buber.database;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ConnectorDBTest {
    @Test
    public void testGetConnection() throws Exception {
        Assert.assertTrue(ConnectorDB.getConnection() != null);
    }
}