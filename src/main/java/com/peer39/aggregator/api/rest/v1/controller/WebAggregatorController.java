package com.peer39.aggregator.api.rest.v1.controller;


import com.peer39.aggregator.api.rest.service.Extractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;
import java.util.Map;
import java.util.Set;


/**
 * Rest controller for extract process
 */
@RestController
@RequestMapping("/api/v1")
public class WebAggregatorController {

    @Autowired
    private Extractor<String, URL> extractor;

    /**
     * Brings the text of a URL for a list of given Web Pages
     *
     * @param URLs - a list of given Web Pages
     * @return Map with extracted text for each valid URL
     */
    @PostMapping("/extract")
    public Map<String, String> getExtractedWebPages(@RequestBody Set<String> URLs) {
        // The business logic should be in the service layer based on the rule of thumb
        // Controllers must be lightweight and pass on requests
        return extractor.extract(URLs);
    }


}
