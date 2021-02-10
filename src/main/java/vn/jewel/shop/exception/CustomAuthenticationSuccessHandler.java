package vn.jewel.shop.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import vn.jewel.shop.model.User;
import vn.jewel.shop.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationSuccessHandler
        implements AuthenticationSuccessHandler {

    @Autowired
    UserServiceImpl userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        String username = authentication.getName();
        User user = userService.findUserActiveByUsername(username);
        if(user!=null){
            httpServletResponse.sendRedirect("/home");
        }
        else {
            Cookie cookie = new Cookie("JSESSIONID", null);
            cookie.setMaxAge(0);
            cookie.setSecure(true);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            httpServletResponse.addCookie(cookie);
            httpServletResponse.sendRedirect("/login?message=error1");
        }
    }
}