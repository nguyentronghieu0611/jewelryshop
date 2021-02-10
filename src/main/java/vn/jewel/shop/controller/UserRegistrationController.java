package vn.jewel.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.jewel.shop.dto.UserRegistrationDto;
import vn.jewel.shop.model.ConfirmationToken;
import vn.jewel.shop.model.User;
import vn.jewel.shop.repository.ConfirmationTokenRepository;
import vn.jewel.shop.repository.UserRepository;
import vn.jewel.shop.service.EmailSenderService;
import vn.jewel.shop.service.UserService;

import javax.validation.Valid;
import java.util.Date;


@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private UserRepository userRepository;

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm() {
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

        User user = userService.save(userDto);
        //send verification email
        ConfirmationToken confirmationToken = new ConfirmationToken(user);
        confirmationTokenRepository.save(confirmationToken);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Hoàn tất đăng ký!");
        mailMessage.setFrom("quochungjewelry@gmail.com");
        mailMessage.setText("Để hoàn tất đăng ký, vui lòng click : "
                +"http://localhost:8081/registration/confirm-account?token="+confirmationToken.getConfirmationToken());
        emailSenderService.sendEmail(mailMessage);
        return "redirect:/registration?success";
    }

    @GetMapping(value="/confirm-account")
    public String confirmUserAccount(@RequestParam("token")String confirmationToken,Model model)
    {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        if(token != null)
        {
            User user = userService.findByEmail(token.getUser().getEmail());
            //kiểm tra token đã hết hạn chưa
            if(token.getCreatedDate().getTime()>new Date(System.currentTimeMillis()-24*60*60*1000).getTime()){
                user.setEnabled(1);
                userRepository.save(user);
                confirmationTokenRepository.delete(token);
                model.addAttribute("err",0);
            }
            else {
                model.addAttribute("err",2);
            }
        }
        else
        {
            model.addAttribute("err",1);
        }
        return "confirm_email";
    }
}
