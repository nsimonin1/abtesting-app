package org.afrinnov.controller;

import java.util.Objects;

import javax.validation.Valid;

import org.afrinnov.AUrlUtils;
import org.afrinnov.controller.form.CarForm;
import org.afrinnov.dto.CarDto;
import org.afrinnov.request.CarRequest;
import org.afrinnov.service.CarService;
import org.ff4j.FF4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static org.afrinnov.controller.data.RDataConfig.aRDataConfig;

@Controller
@RequestMapping(AUrlUtils.CARS_NEW)
public class CarNewController {
    private final CarService carService;
    private final FF4j getFF4j;

    public CarNewController(CarService carService, FF4j getFF4) {
        this.carService = carService;
        this.getFF4j = getFF4;
    }

    @GetMapping
    public ModelAndView getRequest() {
        if (getFF4j.check("new_cars_new")) {
            ModelAndView mav = new ModelAndView("page-react");
            mav.addObject("data", aRDataConfig()
                    .withApp("WEB")
                    .withName("Simon")
                    .withPage("carsNew")
                    .build());
            return mav;
        }
        ModelAndView mav = new ModelAndView("pages/car-new");
        mav.addObject("car", new CarForm());
        return mav;
    }

    @PostMapping
    public String postRequest(@Valid CarRequest car,
                              BindingResult result, ModelMap model) {

        CarDto carDto = carService.createCar(car);
        if (Objects.nonNull(carDto.getErrorMessage())) {
            model.addAttribute("error", carDto.getErrorMessage());
            model.addAttribute("car", car);
            return "pages/car-new";
        }
        return "redirect:/cars/edit?code=" + carDto.getCode();
    }


}
