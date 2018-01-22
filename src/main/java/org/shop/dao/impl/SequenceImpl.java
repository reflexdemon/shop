package org.shop.dao.impl;


import org.shop.dao.Sequence;
import org.shop.model.SequenceId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

/**
 * Created by vprasanna on 6/12/2016.
 */
@Repository
public class SequenceImpl implements Sequence {

    @Autowired
    private MongoOperations mongoOperation;

  @Autowired
  private MongoTemplate mongoTemplate;


  @Override
    public long getNextSequenceId(String key) {
        //get sequence id
        Query query = new Query(Criteria.where("_id").is(key));

        //increase sequence id by 1
        Update update = new Update();
        update.inc("seq", 1);

        //return new increased id
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);

        //this is the magic happened.
//        SequenceId seqId =
//                mongoOperation.findAndModify(query, update, options, SequenceId.class);

    SequenceId seqId = mongoTemplate.findOne(query, SequenceId.class);
        //if no id, throws RuntimeException
        //optional, just a way to tell user when the sequence id is failed to generate.
        if (seqId == null) {
            throw new RuntimeException("Unable to get sequence id for key : " + key);
        }

        return seqId.getSeq();
    }
}
