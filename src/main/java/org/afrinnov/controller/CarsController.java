package org.afrinnov.controller;

import java.util.Objects;

import org.afrinnov.AUrlUtils;
import org.afrinnov.controller.data.RDataConfig;
import org.afrinnov.dto.CarDto;
import org.afrinnov.request.VersionRequest;
import org.afrinnov.service.CarService;
import org.ff4j.FF4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static org.afrinnov.controller.data.RDataConfig.*;

@Controller
@RequestMapping(AUrlUtils.CARS)
public class CarsController extends AbstractFf4jCarsController {

    public CarsController(CarService carService, FF4j getFF4j) {
        super(carService, getFF4j);
    }

    @GetMapping
    public ModelAndView carsRequest() {
        if (checkFeature("new_cars_list")) {
            ModelAndView mav = new ModelAndView("page-react");
            mav.addObject("data", aRDataConfig()
                    .withApp("WEB")
                    .withName("Simon")
                    .withPage("carsList")
                    .build());
            return mav;
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
