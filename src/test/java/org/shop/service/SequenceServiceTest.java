package org.shop.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertTrue;

public class SequenceServiceTest extends AbstractTest {

  @Autowired
  private SequenceService sequenceService;

  @Test
  public void shouldReturnValidSequenceNumber() {
    String key = "order";
    long sequence = sequenceService.getSequenceForOrderNumber(key);
    assertTrue("Should return 1 or larger number", sequence > 0);
  }
}
