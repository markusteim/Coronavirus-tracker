package com.xer0ld.coronatracker.controllers;

import com.xer0ld.coronatracker.services.VirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    VirusDataService virusDataService;
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("locationStats", virusDataService.getAllStats());
        return "home";
    }

}
