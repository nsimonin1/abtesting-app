package org.afrinnov.controller;

import org.afrinnov.service.CarService;
import org.ff4j.FF4j;

public abstract class AbstractFf4jCarsController extends AbstractCarsController {
    protected final FF4j getFF4j;

    public AbstractFf4jCarsController(CarService carService, FF4j getFF4j) {
        super(carService);
        this.getFF4j = getFF4j;
    }

    public boolean checkFeature(String featureName) {
        return getFF4j.check(featureName);
    }
}
