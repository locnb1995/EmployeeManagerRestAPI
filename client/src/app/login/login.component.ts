import { Component, OnInit } from '@angular/core';
import { LoginService } from './login.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {

  constructor(private loginService: LoginService , private router: Router) { }
  notification = '';
  onLogin(user) {
    this.loginService.getUser(user.value).subscribe((data) => {
      if (data.length !== 0) {
        localStorage.setItem('user_id', data[0].user_id);
        localStorage.setItem('username', data[0].username);
        localStorage.setItem('role', data[0].role);
        this.router.navigate(['/home']);
      } else {
        this.notification = 'Invalid username or password';
      }
    });
  }

  ngOnInit() {
  }

}
