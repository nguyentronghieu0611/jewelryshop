package vn.jewel.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminProductController {
    @RequestMapping(value = "/admin/product")
    public String index() {
        return "admin_product/index";
    }
}
