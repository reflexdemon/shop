package org.shop.dao;

import org.shop.model.SequenceId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SequenceIdRepository  extends MongoRepository<SequenceId, String> {
}
