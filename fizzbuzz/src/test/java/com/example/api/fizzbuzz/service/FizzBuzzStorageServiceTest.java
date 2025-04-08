package com.example.api.fizzbuzz.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class FizzBuzzStorageServiceTest {

    @Autowired
    private FizzBuzzStorageService fizzBuzzStorageService;

    @Test
    public void givenCSVFile_whenRead_thenContentsAsExpected() {
        File file = new File("fizzbuzz.csv");
        if (file.exists()) {
            file.delete();
        }
        fizzBuzzStorageService.save(2, 3, 5, "fizz", "buzz");
        fizzBuzzStorageService.save(3, 5, 10, "fizz", "buzz");
        List<String[]> fizzBuzzList = fizzBuzzStorageService.readAllLines();
        assertEquals(3, fizzBuzzList.size());
    }
}
