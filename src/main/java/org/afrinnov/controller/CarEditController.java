package org.afrinnov.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.validation.Valid;

import org.afrinnov.AUrlUtils;
import org.afrinnov.dto.CarDto;
import org.afrinnov.request.CarURequest;
import org.afrinnov.service.CarService;
import org.ff4j.FF4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static org.afrinnov.controller.data.RDataConfig.aRDataConfig;

@Controller
@RequestMapping(AUrlUtils.CARS_EDIT)
public class CarEditController {
    private final CarService carService;
    private final FF4j getFF4j;

    public CarEditController(CarService carService, FF4j getFF4) {
        this.carService = carService;
        this.getFF4j = getFF4;
    }

    @GetMapping
    public ModelAndView getRequest(@RequestParam("code") String code) {

        if (getFF4j.check("new_cars_edit")) {
            Map<String, Object> context = new HashMap<>();
            context.put("code", code);
            ModelAndView mav = new ModelAndView("page-react");
            mav.addObject("data", aRDataConfig()
                    .withApp("WEB")
                    .withName("Simon")
                    .withPage("carsEdit")
                    .withContext(context)
                    .build());
            return mav;
        }

        ModelAndView mav = new ModelAndView("pages/car-edit");
        Optional<CarDto> carDto = carService.oneCar(code);
        if (carDto.isPresent()) {
            mav.addObject("car", carDto.get());
        }
        mav.addObject("error", "NOT_FOUND");
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
