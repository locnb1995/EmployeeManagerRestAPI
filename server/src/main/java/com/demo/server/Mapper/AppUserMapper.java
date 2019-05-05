package com.demo.server.Mapper;

import com.demo.server.Model.AppUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AppUserMapper implements RowMapper<AppUser> {
    @Override
    public AppUser mapRow(ResultSet resultSet, int i) throws SQLException {
        Long id = resultSet.getLong("USER_ID");
        String name = resultSet.getString("USER_NAME");
        String password = resultSet.getString("ENCRYTED_PASSWORD");
        String role = resultSet.getString("ROLE_NAME");

        return new AppUser(id,name,password,role);
    }
}
