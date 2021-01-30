package vn.jewel.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.jewel.shop.dto.UserRegistrationDto;
import vn.jewel.shop.model.User;
import vn.jewel.shop.service.UserService;

import javax.validation.Valid;


@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto,
                                      BindingResult result){

        User existingMail = userService.findByEmail(userDto.getEmail());
        if (existingMail != null){
            result.rejectValue("email", null, "Đã có một tài khoản được đăng ký với email này");
        }
        User existingUsername = userService.findByUserName(userDto.getUserName());
        if(existingUsername!=null){
            result.rejectValue("userName", null, "Đã có một tài khoản được đăng ký với username này");
        }

        if (result.hasErrors()){
            return "registration";
        }

        userService.save(userDto);
        return "redirect:/registration?success";
    }
}
