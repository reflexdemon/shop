package org.shop.service;

import org.shop.dao.SequenceIdRepository;
import org.shop.model.SequenceId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SequenceService {

  @Autowired
  private SequenceIdRepository sequenceIdRepository;


  public long getSequenceForOrderNumber(String key) {
    Optional<SequenceId> sequenceId = sequenceIdRepository.findById(key);
    SequenceId id;
    if (sequenceId.isPresent()) {
      id = sequenceId.get();
      long sequence = id.getSeq() + 1;
      id.setSeq(sequence);
    } else {
      long sequence = 1;
      id = new SequenceId();
      id.setId(key);
      id.setSeq(sequence);
    }

    sequenceIdRepository.save(id);


    return id.getSeq();
  }

}
