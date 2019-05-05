package com.demo.server.DAO;

import com.demo.server.Mapper.XaMapper;
import com.demo.server.Model.Xa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class XaDAO extends JdbcDaoSupport {

    @Autowired
    public XaDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<Xa> getXaByHuyen(String id_huyen){
        String sql = "SELECT * FROM xa WHERE id_huyen = "+id_huyen;
        Object[] params = new Object[] {};
        XaMapper map = new XaMapper();
        List<Xa> list = this.getJdbcTemplate().query(sql,params,map);
        return list;
    }

    public String getNameXaById(String id){
        String sql = "SELECT * FROM xa WHERE id_xa = " + id;
        Object[] params = new Object[] {};
        XaMapper map = new XaMapper();
        List<Xa> list = this.getJdbcTemplate().query(sql,params,map);
        String name = "";
        for (int i = 0 ; i < list.size() ; i++){
            name = list.get(i).getName();
        }
        return name;
    }

    public Long getIdXaByName(String name){
        String sql = "SELECT * FROM xa WHERE name LIKE '"+name+"'";
        Object[] params = new Object[] {};
        XaMapper map = new XaMapper();
        List<Xa> list = this.getJdbcTemplate().query(sql,params,map);
        long id = 0;
        for (int i = 0 ; i < list.size() ; i++){
            id = list.get(i).getId_xa();
        }
        return id;
    }
}
