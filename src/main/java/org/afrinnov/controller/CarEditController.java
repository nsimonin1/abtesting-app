package org.afrinnov.controller;

import org.afrinnov.AUrlUtils;
import org.afrinnov.dto.CarDto;
import org.afrinnov.request.CarURequest;
import org.afrinnov.service.CarService;
import org.afrinnov.tools.FeatureTools;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping(AUrlUtils.CARS_EDIT)
public class CarEditController extends AbstractFf4jCarsController {

    public CarEditController(CarService carService, FeatureTools featureTools) {
        super(carService, featureTools);
    }

    @GetMapping
    public ModelAndView getRequest(@RequestParam("code") String code) {
        if (featureTools.isNewFeatureCarsEditAllow()) {
            Map<String, Object> context = new HashMap<>();
            context.put("code", code);
            return reactPage(context);
        }

        ModelAndView mav = new ModelAndView("pages/car-edit");
        Optional<CarDto> carDto = carService.oneCar(code);
        if (carDto.isPresent()) {
            mav.addObject("car", carDto.get());
        } else {
            mav.addObject("error", "NOT_FOUND");
        }
        return mav;
    }

    @PostMapping
    public String postRequest(@RequestParam("code") String code,
                              @Valid CarURequest car,
                              BindingResult result, ModelMap model) {

        CarDto carDto = carService.updateCar(code, car);
        if (Objects.nonNull(carDto.getErrorMessage())) {
            model.addAttribute("error", carDto.getErrorMessage());
            return "pages/car-edit";
        }
        return "redirect:/cars/edit?code=" + code;
    }


}
