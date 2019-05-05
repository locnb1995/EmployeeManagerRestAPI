package com.demo.server.DAO;

import com.demo.server.Mapper.EmployeeMapper;
import com.demo.server.Model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class EmployeeDAO extends JdbcDaoSupport {

    @Autowired
    public EmployeeDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<Employee> getEmpInfo(String sql){
        Object[] params = new Object[] {};
        EmployeeMapper map = new EmployeeMapper();
        List<Employee> list = this.getJdbcTemplate().query(sql,params,map);
        return list;
    }

    public List<Employee> getEmployeeInfo(){
        String sql = "SELECT nv.id_nhanvien , nv.name , nv.age , t.name as tinh , h.name as huyen , x.name as xa " +
                "FROM nhanvien as nv " +
                "INNER JOIN nhanvien_address as nva ON nv.id_nhanvien = nva.id_nhanvien " +
                "INNER JOIN tinh as t ON nva.id_tinh = t.id_tinh " +
                "INNER JOIN huyen as h ON nva.id_huyen = h.id_huyen " +
                "INNER JOIN xa as x ON nva.id_xa = x.id_xa WHERE 1 ORDER BY id_nhanvien ASC";
        List<Employee> list = getEmpInfo(sql);
        return list;
    }

    public List<Employee> getEmployeeById(String id){
        String sql = "SELECT nv.id_nhanvien , nv.name , nv.age , t.name as tinh , h.name as huyen , x.name as xa " +
                "FROM nhanvien as nv " +
                "INNER JOIN nhanvien_address as nva ON nv.id_nhanvien = nva.id_nhanvien " +
                "INNER JOIN tinh as t ON nva.id_tinh = t.id_tinh " +
                "INNER JOIN huyen as h ON nva.id_huyen = h.id_huyen " +
                "INNER JOIN xa as x ON nva.id_xa = x.id_xa WHERE nv.id_nhanvien=" + id + " ORDER BY id_nhanvien ASC";
        List<Employee> list = getEmpInfo(sql);
        return list;
    }
    public List<Employee> pagination(int rowperpage , String num){
        int page = Integer.parseInt(num);
        int pagination = (page*rowperpage) - rowperpage;
        String sql = "SELECT nv.id_nhanvien , nv.name , nv.age , t.name as tinh , h.name as huyen , x.name as xa " +
                "FROM nhanvien as nv " +
                "INNER JOIN nhanvien_address as nva ON nv.id_nhanvien = nva.id_nhanvien " +
                "INNER JOIN tinh as t ON nva.id_tinh = t.id_tinh " +
                "INNER JOIN huyen as h ON nva.id_huyen = h.id_huyen " +
                "INNER JOIN xa as x ON nva.id_xa = x.id_xa ORDER BY id_nhanvien ASC LIMIT "+pagination+","+rowperpage;

        List<Employee> list = getEmpInfo(sql);
        return list;
    }

    public List<Employee> search(Long id , String name , int age , String tinh){
        String where_id = "";
        String where_name = "";
        String where_age = "";
        String where_tinh = "";
        if(id != null){
            if(!name.equals("") || age != 0 || !tinh.equals("")){
                where_id = "nv.id_nhanvien LIKE '%" + id + "%' AND ";
            }else{
                where_id = "nv.id_nhanvien LIKE '%" + id + "%'";
            }
        }
        if(!name.equals("")){
            if(age != 0 || !tinh.equals("")){
                where_name = "nv.name LIKE '%" + name + "%' AND ";
            }else {
                where_name = "nv.name LIKE '%" + name + "%'";
            }
        }
        if(age != 0){
            if(!tinh.equals("")){
                where_age = "nv.age LIKE '%" + age + "%' AND ";
            }else {
                where_age = "nv.age LIKE '%" + age + "%'";
            }
        }
        if(!tinh.equals("")){
            where_tinh = "t.name LIKE '%" + tinh + "%'";
        }
        String where = where_id + where_name + where_age + where_tinh;
        if(where.equals("")){
           return new ArrayList<Employee>();
        }
        String sql = "SELECT nv.id_nhanvien , nv.name , nv.age , t.name as tinh , h.name as huyen , x.name as xa " +
                "FROM nhanvien as nv " +
                "INNER JOIN nhanvien_address as nva ON nv.id_nhanvien = nva.id_nhanvien " +
                "INNER JOIN tinh as t ON nva.id_tinh = t.id_tinh " +
                "INNER JOIN huyen as h ON nva.id_huyen = h.id_huyen " +
                "INNER JOIN xa as x ON nva.id_xa = x.id_xa WHERE " + where + "ORDER BY nv.id_nhanvien ASC";

        List<Employee> list = getEmpInfo(sql);
        return list;
    }

}
