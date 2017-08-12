package org.shop.dao;

import org.shop.model.PricingInfo;
import org.shop.model.PricingTag;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by vprasanna on 5/22/2016.
 * The interface Pricing repository.
 */
@Repository("pricingRepository")
public interface PricingRepository extends MongoRepository<PricingInfo, String> {
    List<PricingInfo> findAll();


    /**
     * Find by product id pricing info.
     *
     * @param productId  the product id
     * @param pricingTag the pricing tag
     * @return the pricing info
     */
    PricingInfo findByProductIdAndTag(String productId, PricingTag tag);

    /**
     * Find by tag list.
     *
     * @param pricingTag the pricing tag
     * @return the list
     */
    List<PricingInfo> findByTag(PricingTag pricingTag);
}
