package com.example.api.fizzbuzz.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        properties = {"server.port=8080"})
public class FizzBuzzControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void givenAllParameters_ShouldReturnCompleteMessage() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/fizzbuzz?int1=6&int2=8&limit=20&str1=hola&str2=byeg9",
                String.class)).contains("[\"1\",\"2\",\"3\",\"4\",\"5\",\"hola\",\"7\",\"byeg9\",\"9\",\"10\",\"11\",\"hola\",\"13\",\"14\",\"15\",\"byeg9\",\"17\",\"hola\",\"19\",\"20\"]");
    }

    @Test
    void notGivenInt1_ShouldReturnError400Message() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/fizzbuzz?int2=8&limit=20&str1=hola&str2=byeg9",
                String.class)).contains("\"status\":400,\"error\":\"Bad Request\",\"path\":\"/fizzbuzz\"");
    }

    @Test
    void notGivenInt2_ShouldReturnError400Message() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/fizzbuzz?int1=8&limit=20&str1=hola&str2=byeg9",
                String.class)).contains("\"status\":400,\"error\":\"Bad Request\",\"path\":\"/fizzbuzz\"");
    }

    @Test
    void notGivenLimit_ShouldReturnError400Message() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/fizzbuzz?int1=8&int2=20&str1=hola&str2=byeg9",
                String.class)).contains("\"status\":400,\"error\":\"Bad Request\",\"path\":\"/fizzbuzz\"");
    }

    @Test
    void notGivenString1_ShouldReturnError400Message() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/fizzbuzz?int1=8&int2=20&limit=20&str2=byeg9",
                String.class)).contains("\"status\":400,\"error\":\"Bad Request\",\"path\":\"/fizzbuzz\"");
    }

    @Test
    void notGivenString2_ShouldReturnError400Message() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/fizzbuzz?int1=8&int2=20&limit=20&str1=byeg9",
                String.class)).contains("\"status\":400,\"error\":\"Bad Request\",\"path\":\"/fizzbuzz\"");
    }

    @Test
    void givenNoParameters_ShouldReturnError400Message() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/fizzbuzz",
                String.class)).contains("\"status\":400,\"error\":\"Bad Request\",\"path\":\"/fizzbuzz\"");
    }

    @Test
    void givenWrongPath_ShouldReturnError404Message() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/fizzbuzj",
                String.class)).contains("\"status\":404,\"error\":\"Not Found\",\"path\":\"/fizzbuzj\"");
    }

    @Test
    void givenBadFormatInt1_ShouldReturnError400Message() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/fizzbuzz?int1=andr&int2=8&limit=20&str1=hola&str2=byeg9",
                String.class)).contains("\"status\":400,\"error\":\"Bad Request\",\"path\":\"/fizzbuzz\"");
    }

    @Test
    void givenBadFormatInt2_ShouldReturnError400Message() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/fizzbuzz?int1=3&int2=sdft&limit=20&str1=hola&str2=byeg9",
                String.class)).contains("\"status\":400,\"error\":\"Bad Request\",\"path\":\"/fizzbuzz\"");
    }

    @Test
    void givenBadFormatLimit_ShouldReturnError400Message() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/fizzbuzz?int1=3&int2=5&limit=hello&str1=hola&str2=byeg9",
                String.class)).contains("\"status\":400,\"error\":\"Bad Request\",\"path\":\"/fizzbuzz\"");
    }

    @Test
    void givenDifferentFormatStr1_ShouldReturnCorrectMessage() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/fizzbuzz?int1=3&int2=5&limit=20&str1=4&str2=byeg9",
                String.class)).contains("[\"1\",\"2\",\"4\",\"4\",\"byeg9\",\"4\",\"7\",\"8\",\"4\",\"byeg9\",\"11\",\"4\",\"13\",\"14\",\"4byeg9\",\"16\",\"17\",\"4\",\"19\",\"byeg9\"]");
    }

    @Test
    void givenDifferentFormatStr2_ShouldReturnCorrectMessage() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/fizzbuzz?int1=3&int2=5&limit=20&str1=hello&str2=9",
                String.class)).contains("[\"1\",\"2\",\"hello\",\"4\",\"9\",\"hello\",\"7\",\"8\",\"hello\",\"9\",\"11\",\"hello\",\"13\",\"14\",\"hello9\",\"16\",\"17\",\"hello\",\"19\",\"9\"]");
    }

    @Test
    void givenGoodPathStats_ShouldReturnCompleteMessage() {
        File file = new File("fizzbuzz.csv");
        if (file.exists()) {
            file.delete();
        }
        this.restTemplate.getForObject("http://localhost:" + port + "/fizzbuzz?int1=6&int2=8&limit=20&str1=hola&str2=byeg9",String.class);
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/stats",
                String.class)).contains("[\"[6,  8,  20,  hola,  byeg9], count: 1\"]");
    }

    @Test
    void givenWrongPathStats_ShouldReturnError404Message() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/staj",
                String.class)).contains("\"status\":404,\"error\":\"Not Found\",\"path\":\"/staj\"");
    }

    @Test
    void givenGoodPathStatsNoFile_ShouldReturnCompleteMessage() {
        File file = new File("fizzbuzz.csv");
        if (file.exists()) {
            file.delete();
        }
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/stats",
                String.class)).contains("\"status\":500,\"error\":\"Internal Server Error\",\"path\":\"/stats\"");
    }
}
