package org.afrinnov.service;

import static com.google.common.base.Strings.padStart;
import static org.afrinnov.builder.CarBuilder.aCarEntity;
import static org.afrinnov.dto.CarDto.CarDtoBuilder.aCarDto;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.afrinnov.dto.CarDto;
import org.afrinnov.entities.CarEntity;
import org.afrinnov.exception.AfrinnovException;
import org.afrinnov.exception.ErrorInfo;
import org.afrinnov.repository.CarRepository;
import org.afrinnov.request.CarRequest;
import org.afrinnov.request.CarURequest;
import org.afrinnov.request.VersionRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<CarDto> allCars() {
        return carRepository.findAll()
                .stream().map(this::buildCarDto).collect(Collectors.toList());
    }

    @Transactional
    public CarDto createCar(CarRequest carRequest) {
        CarEntity entity = aCarEntity()
                .withCode(generateCode())
                .withName(carRequest.getName())
                .withSerialNumber(carRequest.getSerialNumber())
                .build();
        try {
            carRepository.save(entity);
            return buildCarDto(entity);
        } catch (Exception ex) {
            throw AfrinnovException.makeInstance(ex.getMessage(), ErrorInfo.PERSIST_ENTITY_FAILED);
        }

    }

    @Transactional
    public CarDto updateCar(String code, CarURequest carRequest) {
        CarEntity entity = findCarByCode(code);
        checkCurrentItemVersion(entity, carRequest.getVersion());
        try {
            if (Objects.nonNull(carRequest.getSerialNumber())) {
                entity.setSerialNumber(carRequest.getSerialNumber());
            }
            if (Objects.nonNull(carRequest.getName())) {
                entity.setName(carRequest.getName());
            }
            entity.setVersion(carRequest.getVersion());
            return buildCarDto(entity);
        } catch (Exception ex) {
            throw AfrinnovException.makeInstance(ex.getMessage(), ErrorInfo.UPDATE_ENTITY_FAILED);
        }
    }

    @Transactional
    public CarDto deleteCar(String code, VersionRequest carRequest) {
        CarEntity entity = findCarByCode(code);
        checkCurrentItemVersion(entity, carRequest.getVersion());
        try {

            carRepository.delete(entity);
            return buildCarDto(entity);
        } catch (Exception ex) {
            throw AfrinnovException.makeInstance(ex.getMessage(), ErrorInfo.DELETE_ENTITY_FAILED);
        }
    }

    public Optional<CarDto> oneCar(String code) {
        return carRepository.findOneByCode(code)
                .map(this::buildCarDto);
    }

    private void checkCurrentItemVersion(CarEntity entity, Long version) {
        if (!Objects.equals(version, entity.getVersion())) {
            throw AfrinnovException.makeInstance(ErrorInfo.TO_OLD_ITEM);
        }
    }

    private CarEntity findCarByCode(String code) {
        return carRepository.findOneByCode(code)
                .orElseThrow(() -> AfrinnovException.makeInstance(ErrorInfo.CAR_NOT_FOUND));
    }

    private CarDto buildCarDto(CarEntity entity) {
        return aCarDto()
                .withCode(entity.getCode())
                .withName(entity.getName())
                .withSerialNumber(entity.getSerialNumber())
                .withVersion(entity.getVersion())
                .build();
    }

    private String generateCode() {
        return carRepository.findLastCode()
                .map(this::incrementCode)
                .orElse("00000001");
    }

    private String incrementCode(String code) {
        return padStart(Long.toString(Long.parseLong(code) + 1), 8, '0');
    }


}
