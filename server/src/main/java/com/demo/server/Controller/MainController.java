package com.demo.server.Controller;

import com.demo.server.DAO.*;
import com.demo.server.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MainController {

    @Autowired
    private EmployeeDAO employeeDAO;

    @Autowired
    private TinhDAO tinhDAO;

    @Autowired
    private HuyenDAO huyenDAO;

    @Autowired
    private XaDAO xaDAO;

    @Autowired
    private EmployeeBaseDAO employeeBaseDAO;

    @Autowired
    private EmployeeAddressDAO employeeAddressDAO;

    @Autowired
    private AppUserDAO appUserDAO;

    @Autowired
    private AppSiteDAO appSiteDAO;

    @Autowired
    private UserSiteDAO userSiteDAO;

    private int rowperpage = 3;

    @GetMapping("/employees")
    public List<Employee> getAllEmployee(){
        return employeeDAO.getEmployeeInfo();
    }

    
    @GetMapping("/employee/{id}")
    public List<Employee> getEmployeeById(@PathVariable String id){

        return employeeDAO.getEmployeeById(id);
    }

    
    @GetMapping("/getTinh")
    public List<Tinh> getAllTinh(){
        return tinhDAO.getAllTinh();
    }

    
    @GetMapping("/getHuyenByTinh/{id_tinh}")
    public List<Huyen> getHuyenByTinh(@PathVariable String id_tinh){
        return huyenDAO.getHuyenByTinh(id_tinh);
    }

    
    @GetMapping("/getXaByHuyen/{id_huyen}")
    public List<Xa> getXaByHuyen(@PathVariable String id_huyen){
        return xaDAO.getXaByHuyen(id_huyen);
    }

    
    @GetMapping("/getTinhByID/{id_tinh}")
    public List<String> getTinhByID(@PathVariable String id_tinh){
        List<String> list = new ArrayList<>();
        list.add(tinhDAO.getNameTinhById(id_tinh));
        return list;
    }

    
    @GetMapping("/getHuyenByID/{id_huyen}")
    public List<String> getHuyenByID(@PathVariable String id_huyen){
        List<String> list = new ArrayList<>();
        list.add(huyenDAO.getNameHuyenById(id_huyen));
        return list;
    }

    
    @GetMapping("/getXaByID/{id_xa}")
    public List<String> getXaByID(@PathVariable String id_xa){
        List<String> list = new ArrayList<>();
        list.add(xaDAO.getNameXaById(id_xa));
        return list;
    }
    
    @PostMapping("/addEmp")
    public List<String> addEmp(@RequestBody Employee emp){
        String empname = emp.getName();
        int empage = emp.getAge();
        String emptinh = emp.getTinh();
        String emphuyen = emp.getHuyen();
        String empxa = emp.getXa();
        ArrayList<String> listMess = new ArrayList<>();
        if(!empname.equals("") && empage != 0 && !emptinh.equals("") && !emphuyen.equals("") && !empxa.equals("")){
            employeeBaseDAO.insertemployeeBase(empname,empage);
            long id_nhanvien = employeeBaseDAO.getIdLastEmployee();
            long id_tinh = tinhDAO.getIdTinhByName(emptinh);
            long id_huyen = huyenDAO.getIdHuyenByName(emphuyen);
            long id_xa = xaDAO.getIdXaByName(empxa);

            employeeAddressDAO.insertEmployeeAddress(id_tinh,id_huyen,id_xa,id_nhanvien);
            listMess.add("insert employee success");
            return listMess;
        }
        listMess.add("Fail");
        if(empname.equals("")){
            listMess.add("name cannot be empty");
        }
        if(empage == 0){
            listMess.add("age cannot be empty");
        }
        if(emptinh.equals("")){
            listMess.add("tinh cannot be empty");
        }
        if(emphuyen.equals("")){
            listMess.add("huyen cannot be empty");
        }
        if(empxa.equals("")){
            listMess.add("xa cannot be empty");
        }
        return listMess;
    }
    
    @DeleteMapping("/deleteEmp/{id}")
    public List<String> deleteEmp(@PathVariable String id){
        employeeAddressDAO.deleteEmployeeAddress(id);
        employeeBaseDAO.deleteEmployeeBase(id);
        List<String> mess = new ArrayList<>();
        mess.add("delete emp success");
        return mess;
    }

    
    @PutMapping("/editEmp/{id}")
    public List<String> editEmp(@RequestBody Employee emp , @PathVariable String id){
        String empname = emp.getName();
        int empage = emp.getAge();
        String emptinh = emp.getTinh();
        String emphuyen = emp.getHuyen();
        String empxa = emp.getXa();
        ArrayList<String> listMess = new ArrayList<>();
        if(!empname.equals("") && empage != 0 && !emptinh.equals("") && !emphuyen.equals("") && !empxa.equals("")){
            employeeBaseDAO.updateEmployeeBase(id,empname,empage);
            long id_tinh = tinhDAO.getIdTinhByName(emptinh);
            long id_huyen = huyenDAO.getIdHuyenByName(emphuyen);
            long id_xa = xaDAO.getIdXaByName(empxa);
            employeeAddressDAO.updateEmployeeAddress(id_tinh,id_huyen,id_xa,id);
            listMess.add("update employee success");
            return listMess;
        }
        listMess.add("Fail");
        if(empname.equals("")){
            listMess.add("name cannot be empty");
        }
        if(empage == 0){
            listMess.add("age cannot be empty");
        }
        if(emptinh.equals("")){
            listMess.add("tinh cannot be empty");
        }
        if(emphuyen.equals("")){
            listMess.add("huyen cannot be empty");
        }
        if(empxa.equals("")){
            listMess.add("xa cannot be empty");
        }
        return listMess;
    }

    @GetMapping("/pagination/{page}")
    public List<Employee> pagination(@PathVariable String page){
        List<Employee> list = employeeDAO.pagination(this.rowperpage , page);
        return list;
    }

    @GetMapping("/totalpage")
    public List<Integer> totalPage(){
        List<Employee> totalEmp = employeeDAO.getEmployeeInfo();
        double totalnum = totalEmp.size();
        double total = Math.ceil(totalnum/this.rowperpage);
        int totalpage = (int)total;
        List<Integer> list = new ArrayList<>();
        list.add(totalpage);
        return list;
    }

    
    @PostMapping("/checkUserExist")
    public List<AppUser> checkUserExist(@RequestBody AppUser user){
        String username = user.getUsername();
        String encrypt_password = user.getEncryt_password();
        List<AppUser> list = appUserDAO.findUser(username,encrypt_password);
        return list;
    }

    
    @PostMapping("/search")
    public List<Employee> searchEmp(@RequestBody Employee emp){
        Long id = emp.getId_nhanvien();
        String name = emp.getName();
        int age = emp.getAge();
        String tinh = emp.getTinh();
        List<Employee> list = employeeDAO.search(id,name,age,tinh);
        return list;
    }

    
    @GetMapping("/getAllUser")
    public List<AppUser> getAllUser(){
        List<AppUser> list = appUserDAO.getAllUser();
        return list;
    }

    
    @PutMapping("/updateRole/{id}")
    public List<AppUser> updateRole(@RequestBody AppUser user , @PathVariable String id){
        appUserDAO.updateRole(id,user.getRole());
        return new ArrayList<AppUser>();
    }

    
    @GetMapping("/getListSite")
    public List<AppSite> getListSite(){
        List<AppSite> list = appSiteDAO.getListSite();
        return list;
    }

    
    @GetMapping("/getListSiteOfUser/{id}")
    public List<UserSite> getListSiteOfUser(@PathVariable String id){
        List<UserSite> list = userSiteDAO.getListSiteOfUser(id);
        return list;
    }

    
    @PostMapping("/editViewAccess")
    public List<String> editViewAccess(@RequestBody UserAccessSite userAccessSite){
        String id = Long.toString(userAccessSite.getUser_id());
        List<UserSite> list = userSiteDAO.getListSiteOfUser(id);
        ArrayList<String> listSubmit = userAccessSite.getListView();
        ArrayList<String> listAvailable = new ArrayList<>();
        for(int i = 0 ; i < list.size() ; i++) {
            String id_site = Long.toString(list.get(i).getSite_id());
            listAvailable.add(id_site);
        }
        for(int i = 0 ; i < listSubmit.size() ; i++){
            if(!listAvailable.contains(listSubmit.get(i))){
                userSiteDAO.insertUserSite(id,listSubmit.get(i));
            }
        }
        for(int i = 0 ; i < listAvailable.size() ; i++){
            if(!listSubmit.contains(listAvailable.get(i))){
                userSiteDAO.deleteUserSite(id,listAvailable.get(i));
            }
        }
        return new ArrayList<String>();
    }

    
    @PostMapping("/searchUser")
    public List<AppUser> searchUser(@RequestBody AppUser appUser){
        Long id = appUser.getUser_id();
        String username = appUser.getUsername();
        String role = appUser.getRole();
        List<AppUser> list = appUserDAO.SearchUser(id,username,role);
        return list;
    }

}
