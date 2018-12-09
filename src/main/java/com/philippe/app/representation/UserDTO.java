package com.philippe.app.representation;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class UserDTO {
    private UUID id;

    @NotNull
    private String name;
    private String favouriteColor;
    private int favouriteNumber;
}
