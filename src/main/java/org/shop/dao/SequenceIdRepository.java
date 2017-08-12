package org.shop.dao;

import org.shop.model.SequenceId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface SequenceIdRepository  extends MongoRepository<SequenceId, String> {
}
