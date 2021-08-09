package org.afrinnov.controller;

import org.afrinnov.AUrlUtils;
import org.afrinnov.dto.CarDto;
import org.afrinnov.request.VersionRequest;
import org.afrinnov.service.CarService;
import org.afrinnov.tools.FeatureTools;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.Objects;

@Controller
@RequestMapping(AUrlUtils.CARS)
public class CarsController extends AbstractFf4jCarsController {

    public CarsController(CarService carService, FeatureTools featureTools) {
        super(carService, featureTools);
    }

    @GetMapping
    public ModelAndView carsRequest() {
        if (featureTools.isNewFeatureCarsListAllow()) {
            return reactPage(Collections.emptyMap());
        }
        ModelAndView mav = new ModelAndView("pages/cars");
        mav.addObject("cars", carService.allCars());
        return mav;
    }


    @GetMapping("/delete")
    public String deleteRequest(@RequestParam("code") String code, @RequestParam("version") Long version) {
        VersionRequest request = new VersionRequest();
        request.setVersion(version);
        CarDto carDto = carService.deleteCar(code, request);
        if (Objects.nonNull(carDto.getErrorMessage())) {
            return AUrlUtils.failedRedirect(AUrlUtils.CARS, carDto.getErrorCode());
        }
        return AUrlUtils.redirect(AUrlUtils.CARS);
    }


}
