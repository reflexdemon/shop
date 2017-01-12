import { Injectable } from '@angular/core';
import { Headers, Http,  Response } from '@angular/http';

import { User } from './user';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class UserService {
    private userUrl:string = '/rest/user';  // URL to web api
    private userLogin:string = 'login.htm';  // URL to web api


    constructor(private http: Http) {

    }

    // login(username:string, password:string) {
    //   var headers = new Headers();
    //   let urlSearchParams = new URLSearchParams();
    //   urlSearchParams.append('username', username);
    //   urlSearchParams.append('password', password);
    //   let body = urlSearchParams.toString()
    //     this.http.post(this.userLogin, body)
    //         .toPromise()
    //         .then(
    //           response => console.log(response)
    //         )
    //         .catch(this.handleError);
    // }

    getUser(): Observable<User> {
        return this.http.get(this.userUrl)
               .map((r: Response) => r.json() as User)
               .catch(this.handleError);
    }
    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error); // for demo purposes only
        return Promise.reject(error.message || error);
    }
}
