package com.heoch.foodzip;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/test")
public class TestController {

    @GetMapping("/say")
    public String sayHello(Model model) {
        model.addAttribute("say", "Hello");
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("say", "hello");
//        mav.setViewName("test");
        return "test";
    }
}
