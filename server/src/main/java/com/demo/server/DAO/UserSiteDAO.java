package com.demo.server.DAO;

import com.demo.server.Mapper.UserSiteMapper;
import com.demo.server.Model.UserSite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class UserSiteDAO extends JdbcDaoSupport {

    @Autowired
    public UserSiteDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }


    public List<UserSite> getListSiteOfUser(String id){
        String sql = "SELECT us.user_id , us.site_id ,au.USER_NAME , s.name as site_name , s.url FROM user_site as us INNER JOIN app_user as au ON us.user_id = au.USER_ID INNER JOIN site as s ON us.site_id = s.site_id WHERE us.user_id = " + id;
        Object[] params = new Object[] {};
        UserSiteMapper map = new UserSiteMapper();
        List<UserSite> list = this.getJdbcTemplate().query(sql,params,map);
        return list;
    }

    public void insertUserSite(String user_id , String site_id){
        String sql = "INSERT INTO `user_site`(`user_id`, `site_id`) VALUES ('"+user_id+"','"+site_id+"')";
        this.getJdbcTemplate().update(sql);
    }

    public void deleteUserSite(String user_id , String site_id){
        String sql = "DELETE FROM `user_site` WHERE `user_id` = "+user_id+" AND `site_id` = "+site_id;
        this.getJdbcTemplate().update(sql);
    }

}
