package org.shop.model;

import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * Created by vprasanna on 5/22/2016.
 */
public class Cart {
    @Id
    private String id;
    List<LineItem> lineItems;
    Summary summary;
}
