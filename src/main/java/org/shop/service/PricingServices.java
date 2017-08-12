package org.shop.service;

import org.shop.dao.PricingRepository;
import org.shop.model.PricingInfo;
import org.shop.model.Product;
import org.shop.spring.aop.ProfileExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vprasanna on 5/22/2016.
 */
@Service
public class PricingServices {
    @Autowired
    UserServices userServices;
    @Autowired
    private PricingRepository pricingDao;

    public double getBasePriceByProduct(Product product) {
        return pricingDao.findByProductIdAndTag(product.getId(), userServices.getAuthenticatedUser().getPricingTag()).getBasePrice();
    }

    public double getBasePriceById(String productId) {
        return pricingDao.findByProductIdAndTag(productId, userServices.getAuthenticatedUser().getPricingTag()).getBasePrice();
    }

    public double getTaxByProduct(Product product) {
        return pricingDao.findByProductIdAndTag(product.getId(), userServices.getAuthenticatedUser().getPricingTag()).getTaxPercentage()
                * getBasePriceByProduct(product);
    }

    public PricingInfo getPricingInfo(Product product) {
        return pricingDao.findByProductIdAndTag(product.getId(), userServices.getAuthenticatedUser().getPricingTag());
    }

    public double getTaxById(String productId) {
        return pricingDao.findByProductIdAndTag(productId, userServices.getAuthenticatedUser().getPricingTag()).getTaxPercentage()
                * getBasePriceById(productId);
    }

    public List<Product> applyPricing(List<Product> products) {
        if (null == products || products.isEmpty()) {
            throw new IllegalArgumentException("Supplied product cannot be empty");
        }
        List<Product> newList = new ArrayList<>();
        products.stream().forEach(product -> {
            product.setPricingInfo(getPricingInfo(product));
            newList.add(product);
        });

        return newList;

    }

    public Product applyPricing(Product product) {
        if (null == product) {
            throw new IllegalArgumentException("Supplied product cannot be empty");
        }

        product.setPricingInfo(getPricingInfo(product));

        return product;

    }

    @ProfileExecution
    public void deleteAll() {
        pricingDao.deleteAll();
    }

    @ProfileExecution
    public void save(PricingInfo pricing) {
        pricingDao.save(pricing);
    }

    public PricingInfo getPricingForProduct(Product product) {
        return pricingDao.findByProductIdAndTag(product.getId(), userServices.getAuthenticatedUser().getPricingTag());
    }

    public PricingInfo getPricingForProduct(String productId) {
        return pricingDao.findByProductIdAndTag(productId, userServices.getAuthenticatedUser().getPricingTag());
    }
}
