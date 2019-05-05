package com.demo.server.Mapper;

import com.demo.server.Model.AppSite;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AppSiteMapper implements RowMapper<AppSite> {
    @Override
    public AppSite mapRow(ResultSet resultSet, int i) throws SQLException {
        Long id = resultSet.getLong("site_id");
        String name = resultSet.getString("name");
        return new AppSite(id,name);
    }
}
