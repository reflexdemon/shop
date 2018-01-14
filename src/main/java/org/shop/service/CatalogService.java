package org.shop.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.shop.dao.ProductRepository;
import org.shop.dao.Search;
import org.shop.model.Product;
import org.shop.model.SearchResponse;
import org.shop.spring.aop.ProfileExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * Created by vprasanna on 5/15/2016.
 * The type Inventory service.
 */
@Service
public class CatalogService {

    private static final Log logger = LogFactory.getLog(CatalogService.class);

    private static final int PAGE_SIZE = 10;
    @Autowired
    private Search search;

    @Autowired
    @Qualifier("catalogRepository")
    private ProductRepository productDao;

    @Autowired
    private PricingServices pricingServices;

    /**
     * Find all list.
     *
     * @return the list
     */
    public List<Product> findAll() {
        return pricingServices.applyPricing(productDao.findAll());
    }

    /**
     * Find by id product.
     *
     * @param id the id
     * @return the product
     */
    public Product findById(String id) {
      Optional<Product> product = productDao.findById(id);
      if (product.isPresent()) {
        return pricingServices.applyPricing(product.get());
      }
      return null;
    }

    /**
     * Find by category list.
     *
     * @param category the category
     * @return the list
     */
    public List<Product> findByCategory(String category) {


        return pricingServices.applyPricing(productDao.findByCategory(category));
    }

    /**
     * Search search response.
     *
     * @param keyword the keyword
     * @param page    the page
     * @return the search response
     */
    public SearchResponse search(String keyword, Integer page) {
        //To make page parameter optional
        if (null == page || 0 >= page) {
            page = 1;
        }

//        int limit = PAGE_SIZE;
        int offset = (PAGE_SIZE * page) - PAGE_SIZE;
        SearchResponse response = search.search(keyword, PAGE_SIZE, offset);
        response.setPage(page);
        return response;
    }

    /**
     * Save product.
     *
     * @param product the product
     * @return the product
     */
    @ProfileExecution
    public Product save(final Product product) {
        return productDao.save(product);
    }

    /**
     * Delete all.
     */
    @ProfileExecution
    public void deleteAll() {
        productDao.deleteAll();
    }

    /**
     * Gets categories.
     *
     * @return the categories
     */
    public List<String> getCategories() {
        return search.getCategories();
    }
}
