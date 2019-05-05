package com.demo.server.DAO;

import com.demo.server.Mapper.AppSiteMapper;
import com.demo.server.Model.AppSite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class AppSiteDAO extends JdbcDaoSupport {

    @Autowired
    public AppSiteDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<AppSite> getListSite(){
        String sql = "SELECT * FROM site";
        Object[] params = new Object[] {};
        AppSiteMapper map = new AppSiteMapper();
        List<AppSite> list = this.getJdbcTemplate().query(sql,params,map);
        return list;
    }

}
