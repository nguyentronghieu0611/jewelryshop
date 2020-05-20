package vn.jewel.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductController {
    @RequestMapping(value = "/product")
    public String index() {
        return "product/product";
    }
}
