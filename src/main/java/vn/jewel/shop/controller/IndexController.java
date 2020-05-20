package vn.jewel.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class IndexController {
    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/admin/index")
    public String adminindex() {
        return "admin_index/index";
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String message, final Model model) {
        if (message != null && !message.isEmpty()) {
            if (message.equals("logout")) {
                model.addAttribute("message", "Đã đăng xuất!");
            }
            if (message.equals("error")) {
                model.addAttribute("message", "Tài khoản hoặc mật khẩu không chính xác!");
            }
        }

        return "login";
    }

    @GetMapping("/403")
    public String error403() {
        return "403";
    }

    @GetMapping("/error")
    public String error() {
        return "errors/error";
    }
}
