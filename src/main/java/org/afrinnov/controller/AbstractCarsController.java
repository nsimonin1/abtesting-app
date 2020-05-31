package org.afrinnov.controller;

import org.afrinnov.service.CarService;

public abstract class AbstractCarsController {
    protected final CarService carService;

    public AbstractCarsController(CarService carService) {
        this.carService = carService;
    }
}
