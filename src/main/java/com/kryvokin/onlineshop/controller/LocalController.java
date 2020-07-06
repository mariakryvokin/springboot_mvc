package com.kryvokin.onlineshop.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class LocalController {

    private Logger logger = LogManager.getRootLogger();

    @GetMapping("/local")
    public RedirectView setLocal(@RequestParam("lang") String lang){
        logger.info("Local: {}",lang);
        return new RedirectView("main");
    }
}
