package com.demo.server.Mapper;

import com.demo.server.Model.UserSite;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserSiteMapper implements RowMapper<UserSite> {
    @Override
    public UserSite mapRow(ResultSet resultSet, int i) throws SQLException {
        Long user_id = resultSet.getLong("user_id");
        Long site_id = resultSet.getLong("site_id");
        String user_name = resultSet.getString("USER_NAME");
        String site_name = resultSet.getString("site_name");
        String url = resultSet.getString("url");
        return new UserSite(user_id,site_id,user_name,site_name,url);
    }
}
