package vn.jewel.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminCategoryController {
    @RequestMapping(value = "/admin/category")
    public String index() {
        return "admin_category/index";
    }
}
