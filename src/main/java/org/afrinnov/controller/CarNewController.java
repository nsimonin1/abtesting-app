package org.afrinnov.controller;

import org.afrinnov.AUrlUtils;
import org.afrinnov.controller.form.CarForm;
import org.afrinnov.dto.CarDto;
import org.afrinnov.request.CarRequest;
import org.afrinnov.service.CarService;
import org.afrinnov.tools.FeatureTools;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Objects;

@Controller
@RequestMapping(AUrlUtils.CARS_NEW)
public class CarNewController extends AbstractFf4jCarsController {

    public CarNewController(CarService carService, FeatureTools featureTools) {
        super(carService, featureTools);
    }

    @GetMapping
    public ModelAndView getRequest() {
        if (featureTools.isNewFeatureCarsNewAllow()) {
            return reactPage(Collections.emptyMap());
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
