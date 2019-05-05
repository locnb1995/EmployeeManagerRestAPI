package com.demo.server.DAO;

import com.demo.server.Mapper.AppUserMapper;
import com.demo.server.Model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class AppUserDAO extends JdbcDaoSupport {

    @Autowired
    public AppUserDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<AppUser> getListUser(String sql){
        Object[] params = new Object[] {};
        AppUserMapper map = new AppUserMapper();
        List<AppUser> list = this.getJdbcTemplate().query(sql,params,map);
        return list;
    }

    public List<AppUser> findUser(String username , String encrypt_password){
        String sql = "SELECT u.USER_ID , u.USER_NAME , u.ENCRYTED_PASSWORD , ar.ROLE_NAME FROM `app_user` as u " +
                "INNER JOIN `user_role`as ur ON u.USER_ID = ur.USER_ID " +
                "INNER JOIN `app_role` as ar ON ur.ROLE_ID = ar.ROLE_ID WHERE u.ENABLED = 1 AND u.USER_NAME = '"+username+"'"
                + "AND u.ENCRYTED_PASSWORD = '" + encrypt_password + "'";
        List<AppUser> list = getListUser(sql);
        return list;
    }

    public List<AppUser> getAllUser(){
        String sql = "SELECT u.USER_ID , u.USER_NAME , u.ENCRYTED_PASSWORD , ar.ROLE_NAME FROM `app_user` as u " +
                "INNER JOIN `user_role`as ur ON u.USER_ID = ur.USER_ID " +
                "INNER JOIN `app_role` as ar ON ur.ROLE_ID = ar.ROLE_ID WHERE u.ENABLED = 1 AND u.USER_ID != 5";
        List<AppUser> list = getListUser(sql);
        return list;
    }

    public void updateRole(String id , String role){
        String sql = "UPDATE `user_role` SET `ROLE_ID` = (SELECT ROLE_ID FROM app_role WHERE ROLE_NAME = '"+role+"') " +
                "WHERE `user_role`.`USER_ID` = " + id;
        this.getJdbcTemplate().update(sql);
    }

    public List<AppUser> SearchUser(Long id , String username , String role){
        String where_id = "";
        String where_username = "";
        String where_role = "";
        if(id != null){
            if(!username.equals("") || !role.equals("")) {
                where_id = "u.USER_ID LIKE '%" + id + "%' AND ";
            }else{
                where_id = "u.USER_ID LIKE '%" + id + "%'";
            }
        }
        if(!username.equals("")){
            if(!role.equals("")){
                where_username = "u.USER_NAME LIKE '%" + username + "%' AND ";
            }else{
                where_username = "u.USER_NAME LIKE '%" + username + "%'";
            }
        }
        if(!role.equals("")){
            where_role = "ar.ROLE_NAME LIKE '%" + role + "%'";
        }
        String where = where_id + where_username + where_role;
        if(where.equals("")){
            return new ArrayList<AppUser>();
        }
        String sql = "SELECT u.USER_ID , u.USER_NAME , u.ENCRYTED_PASSWORD , ar.ROLE_NAME FROM `app_user` as u " +
                "INNER JOIN `user_role`as ur ON u.USER_ID = ur.USER_ID " +
                "INNER JOIN `app_role` as ar ON ur.ROLE_ID = ar.ROLE_ID WHERE " + where + " AND u.ENABLED = 1 AND u.USER_ID != 5";
        List<AppUser> list = getListUser(sql);
        return list;
    }

}
