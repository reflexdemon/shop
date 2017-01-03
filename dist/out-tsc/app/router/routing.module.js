var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { HomeComponent } from './../home/home.component';
import { LostComponent } from './../lost/lost.component';
import { LoginComponent } from './../login/login.component';
import { PageComponent } from './../page/page.component';
var appRoutes = [
    { path: '', component: HomeComponent },
    { path: 'page', component: PageComponent },
    { path: 'loginpage', component: LoginComponent },
    { path: '**', component: LostComponent }
];
var AppRoutingModule = (function () {
    function AppRoutingModule() {
    }
    return AppRoutingModule;
}());
AppRoutingModule = __decorate([
    NgModule({
        imports: [
            RouterModule.forRoot(appRoutes, { useHash: false })
        ],
        exports: [
            RouterModule
        ]
    }),
    __metadata("design:paramtypes", [])
], AppRoutingModule);
export { AppRoutingModule };
//# sourceMappingURL=../../../../src/app/router/routing.module.js.map