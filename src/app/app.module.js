var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var platform_browser_1 = require('@angular/platform-browser');
var core_1 = require('@angular/core');
var forms_1 = require('@angular/forms');
var http_1 = require('@angular/http');
var material_1 = require('@angular/material');
require('hammerjs');
var app_component_1 = require('./app.component');
var routing_module_1 = require('./router/routing.module');
var home_component_1 = require('./home/home.component');
var lost_component_1 = require('./lost/lost.component');
var page_component_1 = require('./page/page.component');
var login_component_1 = require('./login/login.component');
var sign_up_component_1 = require('./sign-up/sign-up.component');
var user_service_1 = require('./user.service');
var AppModule = (function () {
    function AppModule() {
    }
    AppModule = __decorate([
        core_1.NgModule({
            declarations: [
                app_component_1.AppComponent,
                lost_component_1.DialogContent,
                home_component_1.HomeComponent,
                lost_component_1.LostComponent,
                page_component_1.PageComponent,
                login_component_1.LoginComponent,
                sign_up_component_1.SignUpComponent
            ],
            imports: [
                platform_browser_1.BrowserModule,
                forms_1.FormsModule,
                http_1.HttpModule,
                material_1.MaterialModule.forRoot(),
                routing_module_1.AppRoutingModule
            ],
            entryComponents: [
                lost_component_1.DialogContent
            ],
            providers: [
                user_service_1.UserService
            ],
            bootstrap: [
                app_component_1.AppComponent
            ]
        })
    ], AppModule);
    return AppModule;
})();
exports.AppModule = AppModule;
//# sourceMappingURL=app.module.js.map