var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var core_1 = require('@angular/core');
require('rxjs/add/operator/toPromise');
var UserService = (function () {
    function UserService(http) {
        this.http = http;
        this.userUrl = '/rest/user'; // URL to web api
        this.userLogin = 'login.htm'; // URL to web api
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
    UserService.prototype.getUser = function () {
        return this.http.get(this.userUrl)
            .map(function (r) { return r.json(); });
    };
    UserService.prototype.handleError = function (error) {
        console.error('An error occurred', error); // for demo purposes only
        return Promise.reject(error.message || error);
    };
    UserService = __decorate([
        core_1.Injectable()
    ], UserService);
    return UserService;
})();
exports.UserService = UserService;
//# sourceMappingURL=user.service.js.map