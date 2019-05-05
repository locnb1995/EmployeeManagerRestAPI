import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HeaderService} from '../header/header.service';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit {

  constructor(private router: Router , private headerService: HeaderService) { }

  ngOnInit() {
    if (localStorage.getItem('username') === null) {
      this.router.navigate(['/login']);
    } else {
      this.headerService.getListSiteByUser(localStorage.getItem('user_id')).subscribe((data) => {
        let permisssion = false;
        for (const i of data) {
          if (i.site_name === 'Contact') {
            permisssion = true;
          }
        }
        if (permisssion === false) {
          this.router.navigate(['/pagenotfound']);
        }
      });
    }
  }

}
