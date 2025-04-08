package com.example.api.fizzbuzz.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestStats {
    private int int1;
    private int int2;
    private int limit;
    private String str1;
    private String str2;

    @Override
    public String toString() {
        return int1 + ", " + int2 + ", " + limit + ", " + str1 + ", " + str2;
    }
}
