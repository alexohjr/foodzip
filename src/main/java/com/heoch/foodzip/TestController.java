package com.heoch.foodzip;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sample")
public class TestController {

    @GetMapping("/say2")
    public String sayHello(Model model) {
        model.addAttribute("say", "Hello");
        return "test";
    }
}
