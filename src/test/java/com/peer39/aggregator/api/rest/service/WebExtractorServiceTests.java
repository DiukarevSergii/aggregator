package com.peer39.aggregator.api.rest.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.MalformedURLException;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class WebExtractorServiceTests {

    @Autowired
    private WebExtractorService webAggregatorService;

    @Test
    public void contextLoads() throws Exception {
        assertThat(webAggregatorService).isNotNull();
    }

    @Test
    public void shouldReturnTheCorrectFirstWorld() throws MalformedURLException {
        // given - precondition or setup
        String url = "https://www.bbc.com";

        String text = webAggregatorService.extract(new URL(url));

        // then - verify the output
        assertThat(text).isNotNull();
        assertThat(text.startsWith("Homepage")).isTrue();
    }
}
