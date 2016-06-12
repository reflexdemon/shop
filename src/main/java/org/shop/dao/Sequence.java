package org.shop.dao;

/**
 * Created by vprasanna on 6/12/2016.
 */
public interface Sequence {

    long getNextSequenceId(String key);

}