package org.shop.service;

import org.springframework.stereotype.Service;

/**
 * Created by vprasanna on 5/30/2016.
 */
@Service
public class CounterService {

    long counter;

    public void init(long counter) {
        this.counter = counter;
    }

    public void init() {
        init(0l);
    }

    public long next() {
        return ++counter;
    }

    public long current() {
        return counter;
    }

    public void reset() {
        init();
    }
}
