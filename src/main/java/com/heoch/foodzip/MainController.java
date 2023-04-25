package com.heoch.foodzip;

import groovyjarjarpicocli.CommandLine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("")
@Slf4j
public class MainController {

    @GetMapping("")
    public String mainPage(Model model) {
        model.addAttribute("say", "Hello");

        List<TestDTO> list = new ArrayList<>();

        for(int i=0; i<10; i++) {
            TestDTO dto = TestDTO.builder().num(i).name(i+"번").build();
            list.add(dto);

//             log.info("####### dto :: " + dto.getName());
        }

        model.addAttribute("list", list);

        return "main";
    }

    @ResponseBody
    @PostMapping("main/search")
    public void search(@RequestParam("searchParam") String param) {

        log.info("##### hi");

        log.info("##### param :: " +  param);
    }

}
