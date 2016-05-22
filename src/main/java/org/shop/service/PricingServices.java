package org.shop.service;

import org.shop.dao.PricingRepository;
import org.shop.model.PricingInfo;
import org.shop.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by vprasanna on 5/22/2016.
 */
@Component
public class PricingServices {
    @Autowired
    private PricingRepository pricingDao;

    @Autowired
    UserServices userServices;

    public double getBasePriceByProduct(Product product) {
        return pricingDao.findByProductId(product.getId(), userServices.getAuthenticatedUser().getPricingTag()).getBasePrice();
    }

    public double getBasePriceById(String productId) {
        return pricingDao.findByProductId(productId, userServices.getAuthenticatedUser().getPricingTag()).getBasePrice();
    }

    public double getTaxByProduct(Product product) {
        return pricingDao.findByProductId(product.getId(), userServices.getAuthenticatedUser().getPricingTag()).getTaxPercentage()
                * getBasePriceByProduct(product);
    }

    public double getTaxById(String productId) {
        return pricingDao.findByProductId(productId, userServices.getAuthenticatedUser().getPricingTag()).getTaxPercentage()
                * getBasePriceById(productId);
    }

    public void deleteAll() {
        pricingDao.deleteAll();
    }

    public void save(PricingInfo pricing) {
        pricingDao.save(pricing);
    }

    public PricingInfo getPricingForProduct(Product product) {
        return pricingDao.findByProductId(product.getId(), userServices.getAuthenticatedUser().getPricingTag());
    }
    public PricingInfo getPricingForProduct(String productId) {
        return pricingDao.findByProductId(productId, userServices.getAuthenticatedUser().getPricingTag());
    }
}
