package com.peer39.aggregator.api.rest.v1.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class WebAggregatorControllerTests {

    @Autowired
    private WebAggregatorController webAggregatorController;

    @Test
    public void contextLoads() throws Exception {
        assertThat(webAggregatorController).isNotNull();
    }
}
