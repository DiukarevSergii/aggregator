package com.peer39.aggregator.api.rest.service;

import java.util.Map;
import java.util.Set;

public interface Extractor<T, S>{
    Map<T, T> extract(Set<T> URLs);
    T extract(S s);
}
