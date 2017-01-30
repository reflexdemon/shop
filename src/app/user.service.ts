import { Injectable } from '@angular/core';
import { Headers, Http,  Response } from '@angular/http';

import { User } from './user';
import { Observable } from 'rxjs';

@Injectable()
export class UserService {
    private userUrl:string = '/rest/user';  // URL to web api
    
    constructor(private http: Http) {

    }

    getUser(): Observable<User> {
        return this.http.get(this.userUrl)
               .map((r: Response) => r.json() as User)
               .catch(this.handleError);
    }


    createUser(user:User): Observable<User> {
        return this.http.post(this.userUrl, user)
               .map((r: Response) => r.json() as User)
               .catch(this.handleError);
    }

    private handleError(error: any): Observable<any> {
        console.log('An error occurred', error); // for demo purposes only
        return Observable.throw(error.json().message || 'Server error');
    }
}
