package com.peer39.aggregator.validation;

import javax.validation.constraints.NotNull;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A processor that validate input URL strings
 *
 * A validator object is not thread-safe and not reentrant.
 *
 *
 */
public abstract class ValidatorHelper{

    /**
     * @param URLs - Set of input string URLs
     * @return Set of URL objects
     *
     * @throws IllegalArgumentException if one of the string values is not a valid url path
     */
    @NotNull
    public static Set<URL> validateURLs(Set<String> URLs) {
        return URLs.stream()
                .map(item -> {
                    try {
                        // Try creating a valid URL object
                        // throws MalformedURLException  if no protocol is specified, or an
                        // unknown protocol is found, or spec is null,
                        // or the parsed URL fails to comply with the specific syntax
                        // of the associated protocol.
                        URL url = new URL(item);
                        // Try creating a valid URL
                        // throws URISyntaxException if this URL is not formatted strictly according to
                        // RFC2396 and cannot be converted to a URI
                        url.toURI();
                        return url;
                    } catch (MalformedURLException | URISyntaxException e) {
                        String message = "The item is not matched to URL pattern: " + item;
                        throw new IllegalArgumentException(message);
                    }
                })
                .collect(Collectors.toSet());
    }

    /**
     * Removes all NULLs, empty and whitespace objects
     *
     * @param URLs - Set of input string URLs
     * @return Set of URL objects
     */
    public static Set<String> filterInvalid(Set<String> URLs) {
        return URLs.parallelStream()
                // remove all null objects
                .filter(Objects::nonNull)
                // remove empty and white space objects
                .filter(s -> s.length() > 0 && s.split(" ").length > 0)
                // since java 11 we can also use statements below
                // to check if the string is empty or contains only white space
                //.filter(Predicate.not(String::isEmpty))
                //.filter(Predicate.not(String::isBlank))
                //
                .collect(Collectors.toSet());

    }
}
