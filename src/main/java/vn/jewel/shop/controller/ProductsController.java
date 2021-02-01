package vn.jewel.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.jewel.shop.model.Product;
import vn.jewel.shop.service.ProductsService;

import java.util.List;

@Controller
public class ProductsController {
    @Autowired
    ProductsService productsService;

    @RequestMapping(value = "/products")
    public String index(@RequestParam("id") String id, Model model) {
        if(id!=null){
            model.addAttribute("id",id);
        }
        return "products/products";
    }

    @GetMapping(value = "/products/getProducts")
    public ResponseEntity<Object> getProducts(@RequestParam("id") int id, @RequestParam(value = "page", defaultValue = "1") int page,
                                              @RequestParam(value = "limit", defaultValue = "10") int limit){
        return new ResponseEntity<Object>(productsService.getProducts(id,page,limit), HttpStatus.OK);
    }
}
