import { Injectable } from '@angular/core';
import { Http , Headers , RequestOptions } from '@angular/http';
import {map} from 'rxjs/operators';
@Injectable()

export class EmployeeService {
    constructor(private http: Http) {}
    getAllEmployee() {
        return this.http.get('http://localhost:8080/employees')
        .pipe(map(res => res.json()));
    }
    getAllTinh() {
        return this.http.get('http://localhost:8080/getTinh')
        .pipe(map(res => res.json()));
    }
    getHuyenByTinh(Id_tinh) {
        return this.http.get('http://localhost:8080/getHuyenByTinh/' + Id_tinh)
        .pipe(map(res => res.json()));
    }
    getXaByHuyen(Id_huyen) {
        return this.http.get('http://localhost:8080/getXaByHuyen/' + Id_huyen)
        .pipe(map(res => res.json()));
    }
    getTinhByID(Id_tinh) {
        return this.http.get('http://localhost:8080/getTinhByID/' + Id_tinh)
        .pipe(map(res => res.json()));
    }
    getHuyenByID(Id_huyen) {
        return this.http.get('http://localhost:8080/getHuyenByID/' + Id_huyen)
        .pipe(map(res => res.json()));
    }
    getXaByID(Id_xa) {
        return this.http.get('http://localhost:8080/getXaByID/' + Id_xa)
        .pipe(map(res => res.json()));
    }
    addEmp(value) {
        const url = 'http://localhost:8080/addEmp';
        const headers = new Headers({ 'Content-Type': 'application/json' });
        const options = new RequestOptions({
            headers: headers
          });
        const body = JSON.stringify(value);
        return this.http.post(url , body , options)
        .pipe(map(res => res.json()));
    }
    deleteEmp(id) {
        return this.http.delete('http://localhost:8080/deleteEmp/' + id)
        .pipe(map(res => res.json()));
    }
    getEmpById(id) {
        return this.http.get('http://localhost:8080/employee/' + id)
        .pipe(map(res => res.json()));
    }
    editEmp(value , id) {
        const url = 'http://localhost:8080/editEmp/' + id;
        const headers = new Headers({ 'Content-Type': 'application/json' });
        const options = new RequestOptions({
            headers: headers
        });
        const body = JSON.stringify(value);
        return this.http.put(url , body , options)
        .pipe(map(res => res.json()));
    }
    pagination(page){
        return this.http.get('http://localhost:8080/pagination/' + page)
        .pipe(map(res => res.json()));
    }
    totalpage() {
        return this.http.get('http://localhost:8080/totalpage')
        .pipe(map(res => res.json()));
    }
    searchEmp(value) {
        const url = 'http://localhost:8080/search';
        const headers = new Headers({ 'Content-Type': 'application/json' });
        const options = new RequestOptions({
            headers: headers
          });
        const body = JSON.stringify(value);
        return this.http.post(url , body , options)
        .pipe(map(res => res.json()));
    }
}