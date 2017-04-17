/* tslint:disable:no-unused-variable */
var testing_1 = require('@angular/core/testing');
var paging_component_1 = require('./paging.component');
describe('PagingComponent', function () {
    var component;
    var fixture;
    beforeEach(testing_1.async(function () {
        testing_1.TestBed.configureTestingModule({
            declarations: [paging_component_1.PagingComponent]
        })
            .compileComponents();
    }));
    beforeEach(function () {
        fixture = testing_1.TestBed.createComponent(paging_component_1.PagingComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });
    it('should create', function () {
        expect(component).toBeTruthy();
    });
});
//# sourceMappingURL=paging.component.spec.js.map