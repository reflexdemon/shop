package org.shop.dao.impl;

import com.mongodb.client.DistinctIterable;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.shop.dao.Search;
import org.shop.model.Product;
import org.shop.model.SearchResponse;
import org.shop.service.PricingServices;
import org.shop.spring.aop.ProfileExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;


/**
 * Created by vprasanna on 5/15/2016.
 * The type Search.
 */
@Component
public class SearchImpl implements Search {
    private static final Log logger = LogFactory.getLog(SearchImpl.class);
    private static final String COLLECTION = "catalog";
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private PricingServices pricingServices;

    @Override
    @ProfileExecution
    public List<String> getCategories() {
      DistinctIterable<String> iterable = mongoTemplate.getCollection(COLLECTION).distinct("category", String.class);
      List<String> finalResponse =
        StreamSupport.stream(iterable.spliterator(), false)
          .collect(toList());
      logger.info(String.format("Found %s records!", finalResponse.size()));
      return finalResponse;
    }

    @Override
    @ProfileExecution
    public SearchResponse search(String keyword, int limit, int offset) {
        SearchResponse response = new SearchResponse();
        Criteria criteria = new Criteria();
        if (null != keyword) {
            criteria.orOperator(Criteria.where("name").regex(keyword, "i"), Criteria.where("description").regex(keyword, "i"), Criteria.where("category").regex(keyword, "i"));
        }

        Sort sort = new Sort(Sort.DEFAULT_DIRECTION, "name", "category", "description");

        Query query = new Query(criteria).with(sort);
        response.setMaxCount(getMaxCount(criteria));
        query.limit(limit).skip(offset);
        List<Product> products = mongoTemplate.find(query, Product.class);


        response.setProducts(pricingServices.applyPricing(products));
        response.setLimit(limit);
        response.setOffSet(offset);

        return response;
    }

    private long getMaxCount(Criteria criteria) {
        Query query = new Query(criteria);
        return mongoTemplate.count(query, COLLECTION);
    }
}
