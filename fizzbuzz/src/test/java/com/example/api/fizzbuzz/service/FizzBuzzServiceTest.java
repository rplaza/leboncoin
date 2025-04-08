package com.example.api.fizzbuzz.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class FizzBuzzServiceTest {

    @Autowired
    private FizzBuzzService fizzBuzzService;

    @Test
    void givenValidLimitZero() {
        List<String> fizzBuzzResult =  fizzBuzzService.generateFizzBuzz(3, 5, 0,
                "fizz",
                "buzz");

        assertEquals(0, fizzBuzzResult.size());
    }

    @Test
    void givenValidLimitOne() {
        List<String> fizzBuzzResult =  fizzBuzzService.generateFizzBuzz(3, 5, 1,
                "fizz",
                "buzz");

        assertEquals(1, fizzBuzzResult.size());
        assertEquals("1", fizzBuzzResult.get(0));
    }

    @Test
    void givenValidInput_whenGenerateFizzBuzz_thenReturnList() {
        List<String> fizzBuzzResult =  fizzBuzzService.generateFizzBuzz(3, 5, 20,
                "fizz",
                "buzz");

        assertEquals(20, fizzBuzzResult.size());

        assertEquals("fizz", fizzBuzzResult.get(2));
        assertEquals("fizz", fizzBuzzResult.get(5));
        assertEquals("fizz", fizzBuzzResult.get(8));
        assertEquals("fizz", fizzBuzzResult.get(11));
        assertEquals("fizz", fizzBuzzResult.get(17));

        assertEquals("buzz", fizzBuzzResult.get(4));
        assertEquals("buzz", fizzBuzzResult.get(9));
        assertEquals("buzz", fizzBuzzResult.get(19));

        assertEquals("fizzbuzz", fizzBuzzResult.get(14));
    }

    @Test
    void givenSameStringInput_whenGenerateFizzBuzz_thenReturnList() {
        List<String> fizzBuzzResult =  fizzBuzzService.generateFizzBuzz(3, 5, 20,
                "fizz",
                "fizz");

        assertEquals(20, fizzBuzzResult.size());

        assertEquals("fizz", fizzBuzzResult.get(2));
        assertEquals("fizz", fizzBuzzResult.get(5));
        assertEquals("fizz", fizzBuzzResult.get(8));
        assertEquals("fizz", fizzBuzzResult.get(11));
        assertEquals("fizz", fizzBuzzResult.get(17));

        assertEquals("fizz", fizzBuzzResult.get(4));
        assertEquals("fizz", fizzBuzzResult.get(9));
        assertEquals("fizz", fizzBuzzResult.get(19));

        assertEquals("fizzfizz", fizzBuzzResult.get(14));
    }
}
