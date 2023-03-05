package com.fmi.wordle.repository;

import java.util.Collection;

public interface WordRepo {

    Collection<String> list();

    String getRandom();

    boolean exists(String word);
}
