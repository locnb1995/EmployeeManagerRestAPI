import { Injectable } from '@angular/core';
import { Http , Headers , RequestOptions } from '@angular/http';
import {map} from 'rxjs/operators';
@Injectable()

export class UserService {
// tslint:disable-next-line: deprecation
    constructor(private http: Http) {}

    getAllUser() {
        return this.http.get('http://localhost:8080/getAllUser')
        .pipe(map(res => res.json()));
    }

    updateRoleUser(value , user_id){
        const url = 'http://localhost:8080/updateRole/' + user_id;
        const headers = new Headers({ 'Content-Type': 'application/json' });
        const options = new RequestOptions({
            headers: headers
          });
        const body = JSON.stringify(value);
        return this.http.put(url , body , options)
        .pipe(map(res => res.json()));
    }

    getListSite() {
        return this.http.get('http://localhost:8080/getListSite')
        .pipe(map(res => res.json()));
    }

    editViewAccess(value){
        const url = 'http://localhost:8080/editViewAccess';
        const headers = new Headers({ 'Content-Type': 'application/json' });
        const options = new RequestOptions({
            headers: headers
          });
        const body = JSON.stringify(value);
        return this.http.post(url , body , options)
        .pipe(map(res => res.json()));
    }

    searchUser(value) {
        const url = 'http://localhost:8080/searchUser';
        const headers = new Headers({ 'Content-Type': 'application/json' });
        const options = new RequestOptions({
            headers: headers
          });
        const body = JSON.stringify(value);
        return this.http.post(url , body , options)
        .pipe(map(res => res.json()));
    }
}