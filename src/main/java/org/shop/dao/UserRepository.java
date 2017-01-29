package org.shop.dao;

import org.shop.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by vprasanna on 5/22/2016.
 */
@Repository("userRepository")
public interface UserRepository extends MongoRepository<User, String> {

    User findByUsername(String username);

    User findByEmail(String email);
}
