/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { ProductListingService } from './product-listing.service';

describe('ProductListingService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ProductListingService]
    });
  });

  it('should ...', inject([ProductListingService], (service: ProductListingService) => {
    expect(service).toBeTruthy();
  }));
});
