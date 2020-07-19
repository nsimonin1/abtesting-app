package org.afrinnov.resource;

import org.afrinnov.dto.CarDto;
import org.afrinnov.exception.AfrinnovException;
import org.afrinnov.exception.ErrorInfo;
import org.afrinnov.request.CarRequest;
import org.afrinnov.request.CarURequest;
import org.afrinnov.request.VersionRequest;
import org.afrinnov.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.afrinnov.dto.CarDto.CarDtoBuilder.aCarDto;

@RestController
@RequestMapping("/api/cars")
public class CarResource {
    private final CarService carService;

    public CarResource(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public ResponseEntity<List<CarDto>> allCars() {
        return ResponseEntity.ok(carService.allCars());
    }

    @GetMapping("/{code}")
    public ResponseEntity<CarDto> oneCar(@PathVariable String code) {
        return ResponseEntity.of(carService.oneCar(code));
    }

    @PostMapping
    public ResponseEntity<CarDto> createCar(@RequestBody @Valid CarRequest carRequest) {
        try {
            return ResponseEntity.ok(carService.createCar(carRequest));
        } catch (AfrinnovException ex) {
            return errorListed(ex);
        } catch (Exception ex) {
            return uncategorizedError(ex);
        }
    }


    @PatchMapping("/{code}")
    public ResponseEntity<CarDto> updateCar(@PathVariable String code,
                                            @RequestBody @Valid CarURequest carRequest) {
        try {
            return ResponseEntity.ok(carService.updateCar(code, carRequest));
        } catch (AfrinnovException ex) {
            return errorListed(ex);
        } catch (Exception ex) {
            return uncategorizedError(ex);
        }
    }


    @DeleteMapping("/{code}")
    public ResponseEntity<CarDto> deleteCar(@PathVariable String code,
                                            @RequestBody @Valid VersionRequest carRequest) {
        try {
            return ResponseEntity.ok(carService.deleteCar(code, carRequest));
        } catch (AfrinnovException ex) {
            return errorListed(ex);
        } catch (Exception ex) {
            return uncategorizedError(ex);
        }
    }

    private ResponseEntity<CarDto> errorListed(AfrinnovException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(aCarDto()
                        .withErrorMessage(ex.getMessageInfo())
                        .withDetailsErrorMessage(ex.getMessage())
                        .withErrorCode(ex.getStatus())
                        .build());
    }

    private ResponseEntity<CarDto> uncategorizedError(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(aCarDto()
                        .withErrorMessage(ErrorInfo.TECHNICAL_ISSUE.getMessage())
                        .withDetailsErrorMessage(ex.getMessage())
                        .withErrorCode(ErrorInfo.TECHNICAL_ISSUE.getCode())
                        .build());
    }
}
