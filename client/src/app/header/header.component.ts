import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HeaderService } from './header.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private router: Router , private headerService: HeaderService) { }

  username = '';
  roleSuperAdmin = false;
  listSite;

  logout() {
    this.router.navigate(['/login']);
    localStorage.clear();
  }

  ngOnInit() {
    this.username = localStorage.getItem('username');
    this.headerService.getListSiteByUser(localStorage.getItem('user_id')).subscribe((data) => {
      this.listSite = data;
    });
    if (localStorage.getItem('role') === 'SUPERADMIN') {
      this.roleSuperAdmin = true;
    }
  }

}
