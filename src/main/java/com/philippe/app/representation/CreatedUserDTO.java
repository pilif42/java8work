package com.philippe.app.representation;

import lombok.Data;

import java.util.UUID;

@Data
public class CreatedUserDTO {
    private UUID id;
    private boolean created;
}
