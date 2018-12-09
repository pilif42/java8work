package com.philippe.app.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class User {
    private UUID id;
    private String name;
    private String favoriteColor;
    private int favoriteNumber;
}
