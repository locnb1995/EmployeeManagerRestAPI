package com.demo.server.DAO;

import com.demo.server.Mapper.EmployeeBaseMapper;
import com.demo.server.Model.EmployeeBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class EmployeeBaseDAO extends JdbcDaoSupport {

    @Autowired
    public EmployeeBaseDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public Long getIdLastEmployee(){
        String sql = "SELECT * FROM nhanvien ORDER BY id_nhanvien DESC LIMIT 1";
        Object[] params = new Object[] {};
        EmployeeBaseMapper map = new EmployeeBaseMapper();
        List<EmployeeBase> list = this.getJdbcTemplate().query(sql,params,map);
        long id = 0;
        for (int i = 0 ; i < list.size() ; i++){
            id = list.get(i).getId_nhanvien();
        }
        return id;
    }

    public void insertemployeeBase(String name , int age){
        String sql = "INSERT INTO `nhanvien` (`name`, `age`) VALUES ('"+name+"', '"+age+"')";
        this.getJdbcTemplate().update(sql);
    }

    public void updateEmployeeBase(String id , String name , int age){
        String sql = "UPDATE `nhanvien` SET `name` = '"+name+"', `age` = '"+age+"' WHERE `nhanvien`.`id_nhanvien` = "+id;
        this.getJdbcTemplate().update(sql);
    }

    public void deleteEmployeeBase(String id_nhanvien){
        String sql = "DELETE FROM `nhanvien` WHERE `nhanvien`.`id_nhanvien` = "+id_nhanvien;
        this.getJdbcTemplate().update(sql);
    }

}
