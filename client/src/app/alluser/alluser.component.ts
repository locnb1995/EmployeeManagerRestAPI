import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from './alluser.service';
import { HeaderService } from '../header/header.service';

@Component({
  selector: 'app-alluser',
  templateUrl: './alluser.component.html',
  styleUrls: ['./alluser.component.css'],
  providers: [UserService]
})
export class AlluserComponent implements OnInit {
  constructor(private router: Router , private userService: UserService , private headerService: HeaderService) { }
  listUser;
  listSiteAccess;
  userid;
  BackToManagerUser = false;
  searchNotification;
  updateUser(u_id) {
    const selectedRole = (document.getElementById(u_id) as HTMLInputElement).value;
    if (selectedRole !== 'select') {
      const user = {
        user_id : u_id,
        role : selectedRole
      };
      this.userService.updateRoleUser(user, u_id).subscribe((data) => {
        this.ngOnInit();
      });
    }
  }
  onListView(user_id) {
    this.userid = user_id;
    this.userService.getListSite().subscribe((data) => {
      const array = [];
      for (const i of data) {
        const key = i.name;
        const obj = {
          name : '',
          status : false,
          site_id : i.id
        };
        obj.name = key;
        this.headerService.getListSiteByUser(user_id).subscribe((data1) => {
          for (const j of data1) {
            obj.status = false;
            if (key === j.site_name) {
              obj.status = true;
              break;
            }
          }
        });
        array.push(obj);
      }
      this.listSiteAccess = array;
    });
  }
  onEditListView(formView) {
    const ListViewAccess = [];
    const objView = {
      user_id : '',
      listView : []
    };
    objView.user_id = formView.value.user_id;
    const arrayView = [];
    for (const i of this.listSiteAccess) {
      const nameView = (document.getElementById(i.name) as HTMLInputElement).checked;
      if (nameView) {
        arrayView.push(i.site_id);
      }
    }
    objView.listView = arrayView;
    this.userService.editViewAccess(objView).subscribe((data) => {
    });
    const closeViewAccessModal: HTMLElement = document.getElementById('closeListViewModal') as HTMLElement;
    closeViewAccessModal.click();
  }
  onSearchUser(formSearch) {
    this.userService.searchUser(formSearch.value).subscribe((data) => {
      if (data.length === 0) {
        this.searchNotification = 'No result';
      } else {
        this.listUser = data;
        this.BackToManagerUser = true;
        this.searchNotification = '';
      }
    });
  }
  backToManagerUser() {
    this.BackToManagerUser = false;
    this.ngOnInit();
  }
  ngOnInit() {
    if (localStorage.getItem('username') === null || localStorage.getItem('role') !== 'SUPERADMIN') {
      localStorage.clear();
      this.router.navigate(['/login']);
    } else {
      this.userService.getAllUser().subscribe((data) => {
        this.listUser = data;
      });
    }
  }

}
