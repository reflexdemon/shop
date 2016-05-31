package org.shop.model;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by vprasanna on 5/30/2016.
 */
public enum Currency {
    INR("en", "IN"), USD("en", "US");
    private String language;
    private String country;

    Currency(String language, String country) {
        this.language = language;
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public String getCountry() {
        return country;
    }

    public String format(double amount) {
        return NumberFormat.getCurrencyInstance(new Locale(getLanguage(), getCountry())).format(amount);
    }

}
