package org.afrinnov.dto;

import javax.persistence.Version;

public class CarDto {
    private String code;
    private String name;
    private String serialNumber;
    private Long version;
    private String errorMessage;
    private int errorCode;
    private String detailsErrorMessage;

    private CarDto() {
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public Long getVersion() {
        return version;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getDetailsErrorMessage() {
        return detailsErrorMessage;
    }

    public static final class CarDtoBuilder {
        private String code;
        private String name;
        private String serialNumber;
        private Long version;
        private String errorMessage;
        private int errorCode;
        private String detailsErrorMessage;

        private CarDtoBuilder() {
        }

        public static CarDtoBuilder aCarDto() {
            return new CarDtoBuilder();
        }

        public CarDtoBuilder withCode(String code) {
            this.code = code;
            return this;
        }

        public CarDtoBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public CarDtoBuilder withSerialNumber(String serialNumber) {
            this.serialNumber = serialNumber;
            return this;
        }

        public CarDtoBuilder withVersion(Long version) {
            this.version = version;
            return this;
        }

        public CarDto build() {
            CarDto carDto = new CarDto();
            carDto.version = this.version;
            carDto.code = this.code;
            carDto.name = this.name;
            carDto.serialNumber = this.serialNumber;
            carDto.errorMessage = errorMessage;
            carDto.errorCode = errorCode;
            carDto.detailsErrorMessage = detailsErrorMessage;
            return carDto;
        }

        public CarDtoBuilder withErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }

        public CarDtoBuilder withErrorCode(int errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public CarDtoBuilder withDetailsErrorMessage(String detailsErrorMessage) {
            this.detailsErrorMessage = detailsErrorMessage;
            return this;
        }
    }
}
