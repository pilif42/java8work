package com.philippe.app.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class LoadFullSupply {
    private String fileLocationGB;
    private String fileLocationISL;
    private String source;
    private LocalDate extractionDate;
    private Boolean initiateNextJob;
}
