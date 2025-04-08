package com.example.api.fizzbuzz.service;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.example.api.fizzbuzz.model.RequestStats;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FizzBuzzStorageService {

    private final static String PATH = "fizzbuzz.csv";

    private final static String HEADERS = "numberOne, numberTwo, limit, wordOne, wordTwo";

    public void save(final Integer numberOne,
                     final Integer numberTwo,
                     final Integer limit,
                     final String wordOne,
                     final String wordTwo) {
        RequestStats requestStats = RequestStats.builder()
                .int1(numberOne)
                .int2(numberTwo)
                .limit(limit)
                .str1(wordOne)
                .str2(wordTwo)
                .build();
        safeAppend(Collections.singletonList(requestStats.toString()));
    }

    private void safeAppend(List<String> dataLines) {
        try {
            Path path = Paths.get(FizzBuzzStorageService.PATH);
            if (!Files.exists(path)) {
                Files.createFile(path);
                Files.write(path,
                        Stream.of(HEADERS)
                                .collect(Collectors.toList()),
                        StandardOpenOption.APPEND
                );
            }
            Files.write(path,
                    dataLines.stream()
                            .filter(this::isValidData)
                            .collect(Collectors.toList()),
                    StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            log.error("[FIZZBUZZ_STORAGE_SERVICE] CSV Append Error: {}", e.getMessage());
        }
    }

    private boolean isValidData(String data) {
        return data!= null &&!data.trim().isEmpty();
    }

    public List<String[]> readAllLines() {
        List<String[]> lines = null;
        Path filePath = null;
        filePath = Paths.get(FizzBuzzStorageService.PATH);

        try (Reader reader = Files.newBufferedReader(filePath)) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                lines = csvReader.readAll();
            }
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }

        return lines;
    }
}
