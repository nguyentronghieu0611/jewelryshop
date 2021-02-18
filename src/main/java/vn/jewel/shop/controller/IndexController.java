package vn.jewel.shop.controller;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.jewel.shop.common.GooglePojo;
import vn.jewel.shop.common.GoogleUtils;
import vn.jewel.shop.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@Controller
public class IndexController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private GoogleUtils googleUtils;

    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/home")
    public String redirect(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return "admin_index/index";
        }
        else {
            return "index";
        }
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
            if (message.equals("error1")) {
                model.addAttribute("message", "Tài khoản chưa được kích hoạt!");
            }
        }

        return "login";
    }

    @RequestMapping("/login-google")
    public String loginGoogle(HttpServletRequest request) throws ClientProtocolException, IOException {
        String code = request.getParameter("code");

        if (code == null || code.isEmpty()) {
            return "redirect:/login?google=error";
        }

        String accessToken = googleUtils.getToken(code);

        GooglePojo googlePojo = googleUtils.getUserInfo(accessToken);
        UserDetails userDetail = googleUtils.buildUser(googlePojo);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null,
                userDetail.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "redirect:/home";
    }

//    @GetMapping("/403")
//    public String er() {
//        return "403";
//    }
//
//    @GetMapping("/error-403")
//    public String error403() {
//        return "errors/error-403";
//    }

//    @GetMapping("/error")
//    public String error() {
//        return "errors/error";
//    }

//    @GetMapping("/error-404")
//    public String error404() {
//        return "errors/error-404";
//    }
}
