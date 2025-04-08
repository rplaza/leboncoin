package com.example.api.fizzbuzz.controller;

import com.example.api.fizzbuzz.service.FizzBuzzCheckerService;
import com.example.api.fizzbuzz.service.FizzBuzzService;
import com.example.api.fizzbuzz.service.FizzBuzzStorageService;
import io.micrometer.common.util.StringUtils;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@RestController
public class FizzBuzzController {

    @Autowired
    private FizzBuzzStorageService fizzBuzzStorageService;

    @Autowired
    private FizzBuzzCheckerService fizzBuzzCheckerService;

    @Autowired
    private FizzBuzzService fizzBuzzService;

    private ExecutorService executor;

    @PostConstruct
    public void init() {
        executor = Executors.newFixedThreadPool(1);
    }

    @GetMapping("/fizzbuzz")
    @Operation(summary = "Generator fizzbuzz list")
    public ResponseEntity<List<String>> getFizzBuzz(@RequestParam final Integer int1,
                                                   @RequestParam final Integer int2,
                                                   @RequestParam final Integer limit,
                                                   @RequestParam final String str1,
                                                   @RequestParam final String str2) {
        ResponseEntity<List<String>> response = null;

        Future<ResponseEntity<List<String>>> future = executor.submit(new Callable<ResponseEntity<List<String>>>() {
            public ResponseEntity<List<String>> call() throws Exception {
                fizzBuzzStorageService.save(int1, int2, limit, str1, str2);
                String validationResult = fizzBuzzCheckerService.checkFizzBuzz(int1, int2, limit, str1, str2);
                if (!StringUtils.isEmpty(validationResult)) {
                    List<String> result = new ArrayList<>();
                    result.add(validationResult);
                    return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
                }
                List<String[]> lines = fizzBuzzStorageService.readAllLines();

                return new ResponseEntity<>(fizzBuzzService.generateFizzBuzz(int1, int2, limit, str1, str2), HttpStatus.OK);
            }
        });

        try {
            response = future.get();
        } catch (InterruptedException | ExecutionException e) {
            List<String> result = new ArrayList<>();
            result.add("INTERNAL_SERVER_ERROR: " + e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @GetMapping("/stats")
    @Operation(summary = "Generator fizzbuzz statistics")
    public ResponseEntity<List<String>> getFizzBuzzStats() {
        List<String[]> lines = fizzBuzzStorageService.readAllLines();

        return new ResponseEntity<>(List.of(fizzBuzzService.getStatsFizzBuzz(lines)), HttpStatus.OK);
    }
}