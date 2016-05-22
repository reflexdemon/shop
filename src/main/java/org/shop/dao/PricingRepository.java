package org.shop.dao;

import org.shop.model.PricingInfo;
import org.shop.model.PricingTag;
import org.shop.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by vprasanna on 5/22/2016.
 * The interface Pricing repository.
 */
public interface PricingRepository extends MongoRepository<PricingInfo, String> {
    List<PricingInfo> findAll();


    /**
     * Find by product id pricing info.
     *
     * @param productId  the product id
     * @param pricingTag the pricing tag
     * @return the pricing info
     */
    PricingInfo findByProductId(String productId, PricingTag pricingTag);

    /**
     * Find by tag list.
     *
     * @param pricingTag the pricing tag
     * @return the list
     */
    List<PricingInfo> findByTag(PricingTag pricingTag);
}
