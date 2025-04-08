package com.example.api.fizzbuzz.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class FizzBuzzCheckerServiceTest {

    @Autowired
    private FizzBuzzCheckerService fizzBuzzCheckerService;

    @Test
    void givenRightInput_whenCheckFizzBuzz_thenReturnEmpty() {
        String validationResult =  fizzBuzzCheckerService.checkFizzBuzz(3, 5, 0, "fizz", "buzz");
        assertEquals("", validationResult);
    }

    @ParameterizedTest
    @CsvSource(value = { ",5,1,fizz,buzz", "3,,1,fizz,buzz", "3,5,,fizz,buzz", ",,,fizz,buzz" }, delimiter = ',')
    void givenSomeNullNumberInput_whenCheckFizzBuzz_thenReturnEmpty(final String int1Str, final String int2Str, final String limitStr, final String word1, final String word2) {
        Integer int1 = int1Str != null ? Integer.parseInt(int1Str) : null;
        Integer int2 = int2Str != null ? Integer.parseInt(int2Str) : null;
        Integer limit = limitStr != null ? Integer.parseInt(limitStr) : null;
        String validationResult =  fizzBuzzCheckerService.checkFizzBuzz(
                int1,
                int2,
                limit,
                word1,
                word2);

        assertEquals("Invalid input in numbers.\n", validationResult);
    }

    @ParameterizedTest
    @CsvSource(value = { "-3,5,1,fizz,buzz", "3,-5,1,fizz,buzz", "3,5,-1,fizz,buzz", "-3,-5,-1,fizz,buzz" }, delimiter = ',')
    void givenSomeNegativeNumberInput_whenCheckFizzBuzz_thenReturnEmpty(final String int1Str, final String int2Str, final String limitStr, final String word1, final String word2) {
        Integer int1 = int1Str != null ? Integer.parseInt(int1Str) : null;
        Integer int2 = int2Str != null ? Integer.parseInt(int2Str) : null;
        Integer limit = limitStr != null ? Integer.parseInt(limitStr) : null;
        String validationResult =  fizzBuzzCheckerService.checkFizzBuzz(
                int1,
                int2,
                limit,
                word1,
                word2);

        assertEquals("Invalid input in numbers. Couldn´t be negative.\n", validationResult);
    }

    @Test
    void givenEqualsNumberInput_whenCheckFizzBuzz_thenReturnEmpty() {
        String validationResult =  fizzBuzzCheckerService.checkFizzBuzz(3, 3, 1,
                "fizz",
                "buzz");

        assertEquals("Invalid input in numbers. Couldn´t be equal.\n", validationResult);
    }

    @Test
    void givenEqualsNumberNegativeInput_whenCheckFizzBuzz_thenReturnEmpty() {
        String validationResult =  fizzBuzzCheckerService.checkFizzBuzz(-3, -3, 1,
                "fizz",
                "buzz");

        assertEquals("Invalid input in numbers. Couldn´t be negative.\nInvalid input in numbers. Couldn´t be equal.\n", validationResult);
    }

    @Test
    void givenEqualsNumberLimitNegativeInput_whenCheckFizzBuzz_thenReturnEmpty() {
        String validationResult =  fizzBuzzCheckerService.checkFizzBuzz(3, 3, - 1,
                "fizz",
                "buzz");

        assertEquals("Invalid input in numbers. Couldn´t be negative.\nInvalid input in numbers. Couldn´t be equal.\n", validationResult);
    }

    @ParameterizedTest
    @CsvSource(value = { "3,5,1,,buzz", "3,5,1,,buzz", "3,5,1,," }, delimiter = ',')
    void givenSomeWordNullInput_whenCheckFizzBuzz_thenReturnEmpty(final String int1Str, final String int2Str, final String limitStr, final String word1, final String word2) {
        Integer int1 = int1Str != null ? Integer.parseInt(int1Str) : null;
        Integer int2 = int2Str != null ? Integer.parseInt(int2Str) : null;
        Integer limit = limitStr != null ? Integer.parseInt(limitStr) : null;
        String validationResult =  fizzBuzzCheckerService.checkFizzBuzz(
                int1,
                int2,
                limit,
                word1,
                word2);

        assertEquals("Invalid input in words. Couldn´t be empty or null\n", validationResult);
    }

    @ParameterizedTest
    @CsvSource(value = { ",5,1,,buzz", "3,,1,,buzz", "3,5,,,buzz", ",5,1,fizz,", "3,,1,fizz,", "3,5,,fizz,", ",,,," }, delimiter = ',')
    void givenSomeNullNumberAndSomeWordNullInput_whenCheckFizzBuzz_thenReturnEmpty(final String int1Str, final String int2Str, final String limitStr, final String word1, final String word2) {
        Integer int1 = int1Str != null ? Integer.parseInt(int1Str) : null;
        Integer int2 = int2Str != null ? Integer.parseInt(int2Str) : null;
        Integer limit = limitStr != null ? Integer.parseInt(limitStr) : null;
        String validationResult =  fizzBuzzCheckerService.checkFizzBuzz(
                int1,
                int2,
                limit,
                word1,
                word2);

        assertEquals("Invalid input in numbers.\nInvalid input in words. Couldn´t be empty or null\n", validationResult);
    }

    @ParameterizedTest
    @CsvSource(value = { "-3,5,1,,buzz", "3,-5,1,,buzz", "3,5,-1,,buzz", "-3,5,1,fizz,", "3,-5,1,fizz,", "3,5,-1,fizz," }, delimiter = ',')
    void givenSomeNegativeNumberAndSomeWordNullInput_whenCheckFizzBuzz_thenReturnEmpty(final String int1Str, final String int2Str, final String limitStr, final String word1, final String word2) {
        Integer int1 = int1Str != null ? Integer.parseInt(int1Str) : null;
        Integer int2 = int2Str != null ? Integer.parseInt(int2Str) : null;
        Integer limit = limitStr != null ? Integer.parseInt(limitStr) : null;
        String validationResult =  fizzBuzzCheckerService.checkFizzBuzz(
                int1,
                int2,
                limit,
                word1,
                word2);

        assertEquals("Invalid input in numbers. Couldn´t be negative.\nInvalid input in words. Couldn´t be empty or null\n", validationResult);
    }

    @ParameterizedTest
    @CsvSource(value = { "3,3,1,,buzz", "3,3,1,fizz," }, delimiter = ',')
    void givenNumbersEqualsSomeWordNullInput_whenCheckFizzBuzz_thenReturnEmpty(final String int1Str, final String int2Str, final String limitStr, final String word1, final String word2) {
        Integer int1 = int1Str != null ? Integer.parseInt(int1Str) : null;
        Integer int2 = int2Str != null ? Integer.parseInt(int2Str) : null;
        Integer limit = limitStr != null ? Integer.parseInt(limitStr) : null;
        String validationResult =  fizzBuzzCheckerService.checkFizzBuzz(
                int1,
                int2,
                limit,
                word1,
                word2);

        assertEquals("Invalid input in numbers. Couldn´t be equal.\nInvalid input in words. Couldn´t be empty or null\n", validationResult);
    }

    @ParameterizedTest
    @CsvSource(value = { "3,3,-1,,buzz", "3,3,-1,fizz," }, delimiter = ',')
    void givenNumbersEqualsLimitNegativeSomeWordNullInput_whenCheckFizzBuzz_thenReturnEmpty(final String int1Str, final String int2Str, final String limitStr, final String word1, final String word2) {
        Integer int1 = int1Str != null ? Integer.parseInt(int1Str) : null;
        Integer int2 = int2Str != null ? Integer.parseInt(int2Str) : null;
        Integer limit = limitStr != null ? Integer.parseInt(limitStr) : null;
        String validationResult =  fizzBuzzCheckerService.checkFizzBuzz(
                int1,
                int2,
                limit,
                word1,
                word2);

        assertEquals("Invalid input in numbers. Couldn´t be negative.\nInvalid input in numbers. Couldn´t be equal.\nInvalid input in words. Couldn´t be empty or null\n", validationResult);
    }
}
