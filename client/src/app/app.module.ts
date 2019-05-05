import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';
import { Routes, RouterModule } from '@angular/router';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { EmployeeComponent } from './employee/employee.component';
import { PagenotfoundComponent } from './pagenotfound/pagenotfound.component';
import { LoginComponent } from './login/login.component';
import { LoginService } from '../app/login/login.service';
import { HeaderService } from '../app/header/header.service'
import { AlluserComponent } from './alluser/alluser.component';
import { HeaderComponent } from './header/header.component';
import { HomepageComponent } from './homepage/homepage.component';
import { AboutComponent } from './about/about.component';
import { FooterComponent } from './footer/footer.component';
import { ContactComponent } from './contact/contact.component';

const appRoutes: Routes = [
  { path: 'getEmp', component: EmployeeComponent },
  { path: 'listUser', component: AlluserComponent },
  { path: 'home', component: HomepageComponent },
  { path: 'about', component: AboutComponent },
  { path: 'contact', component: ContactComponent },
  { path: 'getEmp/:page', component: EmployeeComponent },
  { path: 'pagenotfound', component: PagenotfoundComponent },
  { path: '',
    redirectTo: '/getEmp/1',
    pathMatch: 'full'
  },
  { path: 'login', component: LoginComponent },
  { path: '**', component: PagenotfoundComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    EmployeeComponent,
    PagenotfoundComponent,
    LoginComponent,
    AlluserComponent,
    HeaderComponent,
    HomepageComponent,
    AboutComponent,
    FooterComponent,
    ContactComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpModule,
    FormsModule,
    RouterModule.forRoot(
      appRoutes,
      //{ enableTracing: true } // <-- debugging purposes only
    )
  ],
  providers: [LoginService , HeaderService],
  bootstrap: [AppComponent]
})
export class AppModule { }
