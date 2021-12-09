package org.afrinnov.controller;

import org.afrinnov.service.CarService;
import org.afrinnov.tools.FeatureTools;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import static org.afrinnov.controller.data.RDataConfig.aRDataConfig;

public abstract class AbstractFf4jCarsController extends AbstractCarsController {
    protected final FeatureTools featureTools;

    protected AbstractFf4jCarsController(CarService carService, FeatureTools featureTools) {
        super(carService);
        this.featureTools = featureTools;
    }

    public ModelAndView reactPage(Map<String, Object> context) {
        ModelAndView mav = new ModelAndView("page-react");
        mav.addObject("data", aRDataConfig()
                .withApp("WEB")
                .withName("Simon")
                .withContext(context)
                .build());
        return mav;
    }

}
