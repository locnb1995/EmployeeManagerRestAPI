import { Component, OnInit , ViewChild , ElementRef } from '@angular/core';
import { EmployeeService } from './employee.service';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginService } from '../login/login.service';
import { HeaderService} from '../header/header.service';
@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css'],
  providers: [EmployeeService]
})
export class EmployeeComponent implements OnInit {
  constructor(private employeeService: EmployeeService ,
              private route: ActivatedRoute ,
              private router: Router,
              private loginService: LoginService,
              private headerService: HeaderService) { }
  nhanvien;
  array_tinh;
  array_huyen;
  array_xa;
  empname = '';
  empage = '';
  empid = '';
  name_tinh = '';
  name_huyen = '';
  name_xa = '';
  name_error = '';
  age_error = '';
  tinh_error = '';
  huyen_error = '';
  xa_error = '';
  notification = '';
  totalpage = [];
  showEmployeeModal = false;
  showNotification = false;
  showPage = true;
  searchNotification = '';
  BackToManagerEmp = false;
  ButtonAddEmp = false;
  EditEmpPermission = false;
  DeleteEmpPermission = false;

  onTinhChange(TinhValue) {
    this.employeeService.getHuyenByTinh(TinhValue).subscribe(res => this.array_huyen = res);
    this.employeeService.getTinhByID(TinhValue).subscribe(res => this.name_tinh = res[0]);
  }
  onHuyenChange(HuyenValue) {
    this.employeeService.getXaByHuyen(HuyenValue).subscribe(res => this.array_xa = res);
    this.employeeService.getHuyenByID(HuyenValue).subscribe(res => this.name_huyen = res[0]);
  }
  onXaChange(XaValue) {
    this.employeeService.getXaByID(XaValue).subscribe(res => this.name_xa = res[0]);
  }
  nameError(nameTarget) {
    if (nameTarget === '') {
      this.name_error = 'name cannot empty';
    } else {
      this.name_error = '';
    }
  }
  ageError(ageTarget) {
    if (ageTarget === '') {
      this.age_error = 'age cannot empty';
    } else {
      this.age_error = '';
    }
  }
  submitHome() {
    this.array_tinh = [];
    this.array_huyen = [];
    this.array_xa = [];
    if (this.name_tinh === '') {
      this.tinh_error = 'tinh cannot empty';
    } else {
      this.tinh_error = '';
    }
    if (this.name_huyen === '') {
      this.huyen_error = 'huyen cannot empty';
    } else {
      this.huyen_error = '';
    }
    if (this.name_xa === '') {
      this.xa_error = 'xa cannot empty';
    } else {
      this.xa_error = '';
    }
  }
  onAddEmp(formNhanvien) {
    this.employeeService.addEmp(formNhanvien.value).subscribe((data) => {
      if (data[0] === 'Fail') {
        if (data.includes('name cannot be empty')) {
          this.name_error = 'name cannot be empty';
        }
        if (data.includes('age cannot be empty')) {
          this.age_error = 'age cannot be empty';
        }
        if (data.includes('tinh cannot be empty')) {
          this.tinh_error = 'tinh cannot be empty';
        }
        if (data.includes('huyen cannot be empty')) {
          this.huyen_error = 'huyen cannot be empty';
        }
        if (data.includes('xa cannot be empty')) {
          this.xa_error = 'xa cannot be empty';
        }
      } else {
        this.ngOnInit();
        this.notification = 'Insert Employee Success';
        this.showNotification = true;
        const closeEmpModal: HTMLElement = document.getElementById('closeModal') as HTMLElement;
        closeEmpModal.click();
        formNhanvien.reset();
      }
    });
  }

  deleteEmp(empid) {
    this.employeeService.deleteEmp(empid).subscribe((data) => {
      this.ngOnInit();
      this.notification = 'Delete Employee Success';
      this.showNotification = true;
    });
  }
  editEmp(empid) {
    this.employeeService.getEmpById(empid).subscribe((data) => {
      this.empid = data[0]['id_nhanvien'];
      this.empname = data[0]['name'];
      this.empage = data[0]['age'];
      this.name_tinh = data[0]['tinh'];
      this.name_huyen = data[0]['huyen'];
      this.name_xa = data[0]['xa'];
    });
  }

  onEditEmp(empinfo,empid){
    this.employeeService.editEmp(empinfo.value, empid).subscribe((data) => {
      if (data[0] === 'Fail') {
        if (data.includes('name cannot be empty')) {
          this.name_error = 'name cannot be empty';
        }
        if (data.includes('age cannot be empty')) {
          this.age_error = 'age cannot be empty';
        }
        if (data.includes('tinh cannot be empty')) {
          this.tinh_error = 'tinh cannot be empty';
        }
        if (data.includes('huyen cannot be empty')) {
          this.huyen_error = 'huyen cannot be empty';
        }
        if (data.includes('xa cannot be empty')) {
          this.xa_error = 'xa cannot be empty';
        }
      } else {
        this.ngOnInit();
        this.notification = 'Update Employee Success';
        this.showNotification = true;
        const closeEmpModal: HTMLElement = document.getElementById('closeEditModal') as HTMLElement;
        closeEmpModal.click();
        empinfo.reset();
      }
    });
  }
  logout() {
    this.router.navigate(['/login']);
    localStorage.clear();
  }
  onSearchEmp(formSearch) {
    this.searchNotification = '';
    this.employeeService.searchEmp(formSearch.value).subscribe((data) => {
      if (data.length === 0) {
        this.searchNotification = 'No result';
      } else {
        this.nhanvien = data;
        this.showPage = false;
        this.BackToManagerEmp = true;
        this.ButtonAddEmp = false;
      }
    });
  }
  ngOnInit() {
    if (localStorage.getItem('username') === null) {
      this.router.navigate(['/login']);
    } else {
      if (localStorage.getItem('role') === 'ADMIN' || localStorage.getItem('role') === 'SUPERADMIN') {
        this.EditEmpPermission = true;
        this.DeleteEmpPermission = true;
        this.ButtonAddEmp = true;
      }
      this.route.paramMap.subscribe(params => {
        this.headerService.getListSiteByUser(localStorage.getItem('user_id')).subscribe((data) => {
          let permisssion = false;
          for (const i of data) {
            if (i.site_name === 'Employee') {
              permisssion = true;
            }
          }
          if (permisssion === false) {
            this.router.navigate(['/pagenotfound']);
          }
        });
        const page = Number(params.get('page'));
        this.employeeService.totalpage().subscribe((data) => {
          const total = Number(data[0]);
          if (isNaN(page) || page < 1 || page > total) {
            this.router.navigate(['/getEmp/1']);
          } else {
            this.totalpage = [];
            this.employeeService.pagination(page).subscribe(res => this.nhanvien = res);
            this.employeeService.getAllTinh().subscribe(res => this.array_tinh = res);
            for (let i = 1 ; i <= total ; i++) {
              this.totalpage.push(i);
            }
          }
        });
      });
    }
  }
}
