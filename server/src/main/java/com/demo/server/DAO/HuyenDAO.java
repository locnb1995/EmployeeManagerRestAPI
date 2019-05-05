package com.demo.server.DAO;

import com.demo.server.Mapper.HuyenMapper;
import com.demo.server.Model.Huyen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class HuyenDAO extends JdbcDaoSupport {

    @Autowired
    public HuyenDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<Huyen> getHuyenByTinh(String id_tinh){
        String sql = "SELECT * FROM huyen WHERE id_tinh = "+id_tinh;
        Object[] params = new Object[] {};
        HuyenMapper map = new HuyenMapper();
        List<Huyen> list = this.getJdbcTemplate().query(sql,params,map);
        return list;
    }

    public String getNameHuyenById(String id){
        String sql = "SELECT * FROM huyen WHERE id_huyen = " + id;
        Object[] params = new Object[] {};
        HuyenMapper map = new HuyenMapper();
        List<Huyen> list = this.getJdbcTemplate().query(sql,params,map);
        String name = "";
        for (int i = 0 ; i < list.size() ; i++){
            name = list.get(i).getName();
        }
        return name;
    }

    public Long getIdHuyenByName(String name){
        String sql = "SELECT * FROM huyen WHERE name LIKE '"+name+"'";
        Object[] params = new Object[] {};
        HuyenMapper map = new HuyenMapper();
        List<Huyen> list = this.getJdbcTemplate().query(sql,params,map);
        long id = 0;
        for (int i = 0 ; i < list.size() ; i++){
            id = list.get(i).getId_huyen();
        }
        return id;
    }

}
