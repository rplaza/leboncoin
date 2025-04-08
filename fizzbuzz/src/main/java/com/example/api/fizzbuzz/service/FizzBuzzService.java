package com.example.api.fizzbuzz.service;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class FizzBuzzService {

    public List<String> generateFizzBuzz(final int numberOne,
                                         final int numberTwo,
                                         final int limit,
                                         final String wordOne,
                                         final String wordTwo) {
        List<String> result = new ArrayList<>();
        IntStream.rangeClosed(1, limit)
                .mapToObj(
                        i -> i % numberOne == 0 ?
                                (i % numberTwo == 0 ? (wordOne + wordTwo) : wordOne) :
                                (i % numberTwo == 0 ? wordTwo : i))
                .forEach(i -> result.add(i.toString()));

        return result;
    }

    public String getStatsFizzBuzz(final List<String[]> lines) {
        List<String> joinedLines = new ArrayList<>();
        // Variable to store the entry with maximum price
        Map.Entry<String, Long> maxEntry = null;

        lines.forEach(item -> joinedLines.add(Arrays.toString(item)));

        Map<String, Long> group = joinedLines.stream()
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));

        group.entrySet().forEach(c -> System.out.println(c.getKey() + " - " + c.getValue()));

        // Iterate through each entry in the map
        for (Map.Entry<String, Long> entry : group.entrySet()) {
            // If maxEntry is null OR current entry's value is greater than maxEntry's value
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
                maxEntry = entry;
            }
        }

        return maxEntry.getKey() + ", count: " + maxEntry.getValue();
    }


}