package org.afrinnov.controller.form;

import javax.validation.constraints.NotEmpty;

public class CarForm {
    @NotEmpty
    private String name;
    @NotEmpty
    private String serialNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
