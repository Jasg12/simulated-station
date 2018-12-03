package com.sjsu.cmpe.sstreet.simulatedstation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UIController {

    @GetMapping("/")
    public String getDashboard(Model model) {
        model.addAttribute("title", "Dashboard");
        return "dashboard";
    }

}
