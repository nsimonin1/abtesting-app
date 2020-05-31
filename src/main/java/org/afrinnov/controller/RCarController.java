package org.afrinnov.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rnav")
public class RCarController {

    @GetMapping
    public String gotoPage() {
        return "page-react";
    }
}
