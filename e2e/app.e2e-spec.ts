import { ShopPage } from './app.po';

describe('shop App', function() {
  let page: ShopPage;

  beforeEach(() => {
    page = new ShopPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
