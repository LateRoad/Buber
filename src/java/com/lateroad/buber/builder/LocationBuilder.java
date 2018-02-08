package com.lateroad.buber.builder;

import com.lateroad.buber.entity.Location;
import com.lateroad.buber.exception.BuberSQLException;
import com.lateroad.buber.exception.BuberUnsupportedOperation;
import com.lateroad.buber.map.Geodecoder;
import org.json.JSONException;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LocationBuilder implements StatementBuilder<Location> {

    @Override
    public Location build(ResultSet resultSet) {
        Location location = new Location();
        try {
            location = Geodecoder.decode(resultSet.getString("lat"), resultSet.getString("lng"));
            location.setLogin(resultSet.getString("login"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return location;
    }

    @Override
    public void makeInsertStatement(String login, Location location, PreparedStatement statement) {
        try {
            statement.setString(1, login);
            statement.setString(2, location.getCountry());
            statement.setString(3, location.getCity());
            statement.setString(4, location.getStreet());
            statement.setString(5, location.getHouseNumber());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void makeUpdateStatement(String login, Location location, PreparedStatement statement) {
        try {
            statement.setString(1, location.getLat());
            statement.setString(2, location.getLng());
            statement.setString(3, login);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void makeSecurityInsertStatement(String login, String password, Location entity, PreparedStatement statement) throws BuberSQLException {
        throw new BuberUnsupportedOperation();
    }
}
