package org.afrinnov.request;

import javax.validation.constraints.NotEmpty;

public class CarRequest {
    @NotEmpty
    private final String name;
    @NotEmpty
    private final String serialNumber;

    public CarRequest(@NotEmpty String name, @NotEmpty String serialNumber) {
        this.name = name;
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }
}
