package vn.jewel.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IntroductionController {
    @RequestMapping(value = "/introduction")
    public String index() {
        return "introduction/index";
    }
}
