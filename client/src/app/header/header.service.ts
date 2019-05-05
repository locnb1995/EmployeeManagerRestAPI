import { Injectable } from '@angular/core';
import { Http , Headers , RequestOptions } from '@angular/http';
import {map} from 'rxjs/operators';
@Injectable()

export class HeaderService {
    // tslint:disable-next-line: deprecation
    constructor(private http: Http) {}
    getListSiteByUser(id) {
        const url = 'http://localhost:8080/getListSiteOfUser/' + id;
        return this.http.get(url)
        .pipe(map(res => res.json()));
    }
}