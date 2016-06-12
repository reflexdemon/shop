package org.shop.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by vprasanna on 5/22/2016.
 */
@Document(collection = "pricingInfo")
public class PricingInfo {
    @Id
    @Indexed
    private String id;
    @Indexed
    private String productId;

    private double basePrice;
    private double taxPercentage;

    private Currency currency;
    @Indexed
    private PricingTag tag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public double getTaxPercentage() {
        return taxPercentage;
    }

    public void setTaxPercentage(double taxPercentage) {
        this.taxPercentage = taxPercentage;
    }

    public PricingTag getTag() {
        return tag;
    }

    public void setTag(PricingTag tag) {
        this.tag = tag;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "PricingInfo{" +
                "id='" + id + '\'' +
                ", productId='" + productId + '\'' +
                ", basePrice=" + basePrice +
                ", taxPercentage=" + taxPercentage +
                ", currency=" + currency +
                ", tag=" + tag +
                '}';
    }
}
