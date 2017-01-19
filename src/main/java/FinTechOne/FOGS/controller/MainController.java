package FinTechOne.FOGS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    static final String springDataRestBasePath = "/api";
    static final String Id = "/{idntty}";
    static final String approve = "/approve";
    static final String filter = "/filter";

    @RequestMapping("/")
    public String app() {
        return "index";
    }

}
