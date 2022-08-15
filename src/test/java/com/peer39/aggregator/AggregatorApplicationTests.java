package com.peer39.aggregator;

import com.peer39.aggregator.api.rest.service.WebExtractorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AggregatorApplicationTests {

	@Autowired
	private WebExtractorService webExtractorService;

	@Test
	void contextLoads() {
		assertThat(webExtractorService).isNotNull();
	}

}
