package com.peer39.aggregator.api.rest.service;

import com.peer39.aggregator.validation.ValidatorHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.validation.constraints.NotNull;
import java.net.URL;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class WebExtractorService implements Extractor<String, URL> {

    Logger logger = LogManager.getLogger(WebExtractorService.class);

    /**
     * Brings the text of a URL for a list of given Web Pages
     *
     * @param URLs - a list of given Web Pages
     * @return Map with extracted text for each valid URL
     */
    public Map<String, String> extract(Set<String> URLs) {
        URLs = ValidatorHelper.filterInvalid(URLs);
        if (URLs.size() == 0) {
            String message = "Set of URLs is empty";
            logger.warn(message);
            return Collections.singletonMap("WARNING", message);
        }
        logger.info("URLs before validation: " + URLs.toString());
        Set<URL> validURLs;
        try {
            validURLs = ValidatorHelper.validateURLs(URLs);
            logger.info("URLs after validation: " + validURLs.toString());
            return validURLs.stream()
                    .collect(Collectors.toMap(URL::toString, this::extract));
        } catch (Exception e) {
            return Collections.singletonMap("ERROR", e.getMessage());
        }
    }

    /**
     * Brings the text of a URL
     *
     * @param url - a URL link
     * @return Extracted text from the URL
     */
    @NotNull
    @Override
    public String extract(URL url) {
        logger.info("Extract text from URL: " + url);
        try {
            Document document = Jsoup.parse(url, 5000);
            return document.body().text();
        } catch (Exception e) {
            String errorMessage = "Unable to parse the web page: " + e;
            logger.error(errorMessage, e);
            throw new IllegalStateException(errorMessage, e);
        }
    }
}
