package com.heoch.foodzip;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {

    @GetMapping("")
    public String sayHello(Model model) {
        model.addAttribute("say", "Hello");
        return "test";
    }

    @GetMapping("/test")
    public String bootstrapTest() {
        return "bootstrap";
    }



}
