package org.afrinnov.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(setterPrefix = "with")
public class CarDto {
    private String code;
    private String name;
    private String serialNumber;
    private Long version;
    private String errorMessage;
    private int errorCode;
    private String detailsErrorMessage;


}
