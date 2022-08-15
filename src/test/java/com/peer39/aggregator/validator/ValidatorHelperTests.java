package com.peer39.aggregator.validator;

import com.peer39.aggregator.validation.ValidatorHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ValidatorHelperTests {

    @Test
    public void shouldReturnAnEmptySet() {
        // given - precondition or setup
        // Creating a Stream of string values
        Stream<String> stream = Stream.of("", " ", null);

        // Using Stream.collect() to collect the
        // elements of stream in a container.
        Set<String> streamSet = stream.collect(Collectors.toSet());

        Set<String> result = ValidatorHelper.filterInvalid(streamSet);

        // then - verify the output
        assertThat(result).isNotNull();
        assertThat(result.isEmpty()).isTrue();
    }

    @Test
    public void shouldReturnNonEmptySetWithSingleItem() {
        // given - precondition or setup
        // Creating a Stream of string values
        String expectedValue = "www.google.com";
        Stream<String> stream = Stream.of("", " ", null, expectedValue);

        // Using Stream.collect() to collect the
        // elements of stream in a container.
        Set<String> streamSet = stream.collect(Collectors.toSet());

        Set<String> result = ValidatorHelper.filterInvalid(streamSet);

        // then - verify the output
        assertThat(result).isNotNull();
        assertThat(result.isEmpty()).isFalse();
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.iterator().next()).isEqualTo(expectedValue);
    }

    @Test
    public void shouldThrowError() {
        // given - precondition or setup
        // Creating a Stream of string values
        Stream<String> stream = Stream.of("http:// www.peer39.org/");

        // Using Stream.collect() to collect the
        // elements of stream in a container.
        Set<String> streamSet = stream.collect(Collectors.toSet());

        Assertions.assertThrows(IllegalArgumentException.class, () -> ValidatorHelper.validateURLs(streamSet));
    }

    @Test
    public void shouldNotThrowError() {
        // given - precondition or setup
        // Creating a Stream of string values
        Stream<String> stream = Stream.of("http://peer39.org/");

        // Using Stream.collect() to collect the
        // elements of stream in a container.
        Set<String> streamSet = stream.collect(Collectors.toSet());

        Assertions.assertDoesNotThrow(() -> ValidatorHelper.validateURLs(streamSet));
    }
}
