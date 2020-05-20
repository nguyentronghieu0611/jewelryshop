package vn.jewel.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductsController {
    @RequestMapping(value = "/products")
    public String index() {
        return "products/products";
    }
}
