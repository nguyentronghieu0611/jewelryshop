package vn.jewel.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.jewel.shop.model.User;
import vn.jewel.shop.repository.UserRepository;

import java.util.List;


@Controller
public class IndexController {
    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/admin/index")
    public String adminindex() {
        List<User> list = userRepository.findAll();
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
