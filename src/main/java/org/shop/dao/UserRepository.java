package org.shop.dao;

import org.shop.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by vprasanna on 5/22/2016.
 */
public interface UserRepository extends MongoRepository<User, String> {

    public User findByUsername(String username);
}