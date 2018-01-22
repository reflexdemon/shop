package org.shop.dao.impl;

import org.junit.Test;
import org.shop.dao.Sequence;
import org.shop.service.AbstractTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertTrue;

public class SequenceImplTest extends AbstractTest {


  @Autowired
  Sequence sequence;

  @Test
  public void getNextSequenceId() {
    long value = sequence.getNextSequenceId("orderid");

    assertTrue("Sequence is generated", value != 0);


  }
}
