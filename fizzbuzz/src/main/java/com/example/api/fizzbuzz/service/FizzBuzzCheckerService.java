package com.example.api.fizzbuzz.service;

import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class FizzBuzzCheckerService {

    public String checkFizzBuzz(final Integer numberOne,
                                final Integer numberTwo,
                                final Integer limit,
                                final String wordOne,
                                final String wordTwo) {
        String result = "";
        if (numberOne == null || numberTwo == null || limit == null) {
            result = "Invalid input in numbers.\n";
        } else {
            if (numberOne < 0 || numberTwo < 0 || limit < 0) {
                result = "Invalid input in numbers. Couldn´t be negative.\n";
            }
            if (numberOne.equals(numberTwo)) {
                result += "Invalid input in numbers. Couldn´t be equal.\n";
            }
        }
        if (StringUtils.isEmpty(wordOne) || StringUtils.isEmpty(wordTwo)) {
            result += "Invalid input in words. Couldn´t be empty or null\n";
        }

        return result;
    }
}
