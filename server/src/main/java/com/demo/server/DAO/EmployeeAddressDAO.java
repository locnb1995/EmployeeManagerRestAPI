package com.demo.server.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@Repository
@Transactional
public class EmployeeAddressDAO extends JdbcDaoSupport {

    @Autowired
    public EmployeeAddressDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public void insertEmployeeAddress(long id_tinh , long id_huyen , long id_xa , long id_nhanvien){
        String sql = "INSERT INTO `nhanvien_address` (`id_tinh`, `id_huyen`, `id_xa`, `id_nhanvien`) VALUES ('"+id_tinh+"', '"+id_huyen+"', '"+id_xa+"', '"+id_nhanvien+"')";
        this.getJdbcTemplate().update(sql);
    }

    public void updateEmployeeAddress(long id_tinh , long id_huyen , long id_xa , String id_nhanvien){
        String sql = "UPDATE `nhanvien_address` SET `id_tinh` = '"+id_tinh+"', `id_huyen` = '"+id_huyen+"', `id_xa` = '"+id_xa+"' WHERE `nhanvien_address`.`id_nhanvien` = "+id_nhanvien;
        this.getJdbcTemplate().update(sql);
    }

    public void deleteEmployeeAddress(String id_nhanvien){
        String sql = "DELETE FROM `nhanvien_address` WHERE `nhanvien_address`.`id_nhanvien` = "+id_nhanvien;
        this.getJdbcTemplate().update(sql);
    }

}
