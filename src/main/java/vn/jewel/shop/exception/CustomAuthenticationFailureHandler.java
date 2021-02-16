package vn.jewel.shop.exception;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationFailureHandler
        implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        e.getLocalizedMessage();
        if(e.getCause() instanceof UserNotEnableException){
            httpServletResponse.sendRedirect("/login?message=error1");
        }
        else {
            httpServletResponse.sendRedirect("/login?message=error");
        }

    }
}