package org.afrinnov.builder;

import org.afrinnov.entities.CarEntity;

public class CarBuilder {
    private String code;
    private String name;
    private String serialNumber;

    private CarBuilder() {
    }

    public static CarBuilder aCarEntity() {
        return new CarBuilder();
    }

    public CarBuilder withCode(String code) {
        this.code = code;
        return this;
    }

    public CarBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CarBuilder withSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
        return this;
    }

    public CarEntity build() {
        CarEntity entity = new CarEntity();
        entity.setCode(code);
        entity.setName(name);
        entity.setSerialNumber(serialNumber);
        return entity;
    }
}
