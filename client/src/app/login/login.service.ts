import { Injectable } from '@angular/core';
import { Http , Headers , RequestOptions } from '@angular/http';
import { Router } from '@angular/router';
import {map} from 'rxjs/operators';
@Injectable()

export class LoginService {
    constructor(private http: Http , private router: Router) {}
    getUser(value) {
        const url = 'http://localhost:8080/checkUserExist';
        const headers = new Headers({ 'Content-Type': 'application/json' });
        const options = new RequestOptions({
            headers: headers
        });
        const body = JSON.stringify(value);
        return this.http.post(url , body , options)
        .pipe(map(res => res.json()));
    }
}