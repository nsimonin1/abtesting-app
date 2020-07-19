package org.afrinnov.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static org.afrinnov.controller.data.RDataConfig.aRDataConfig;

@Controller
@RequestMapping("/modern")
public class RCarController {

    @GetMapping("/**")
    public ModelAndView gotoPage() {
        ModelAndView mav = new ModelAndView("page-react");
        mav.addObject("data", aRDataConfig()
                .withApp("WEB")
                .withName("Simon")
                .withPage("carsList")
                .build());
        return mav;
    }
}
