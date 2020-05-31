package org.afrinnov.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CarURequest {

    private final String name;
    private final String serialNumber;
    @NotNull
    private final Long version;

    public CarURequest(@NotEmpty String name, @NotEmpty String serialNumber, @NotNull Long version) {
        this.name = name;
        this.serialNumber = serialNumber;
        this.version = version;
    }

    public Long getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }
}
