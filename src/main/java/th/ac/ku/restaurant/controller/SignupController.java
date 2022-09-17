package th.ac.ku.restaurant.controller;

import org.springframework.validation.BindingResult;
import th.ac.ku.restaurant.dto.SignupDto;
//import th.ac.ku.restaurant.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import th.ac.ku.restaurant.service.SignupService;
import th.ac.ku.restaurant.dto.SignupDto;

import javax.validation.Valid;

@Controller
public class SignupController {
    @Autowired
    private SignupService signupService;

    @GetMapping("/signup")
    public String getSignupPage(SignupDto user) {
        return "signup";
    }

    @PostMapping("/signup")
    public String signupUser(@Valid SignupDto user, BindingResult result, Model model)
    {
        if (result.hasErrors())
            return "signup";
        if (signupService.isUsernameAvailable(user.getUsername())) {
            signupService.createUser(user);
            model.addAttribute("signupSuccess", true);
        } else {
            model.addAttribute("signupError", "Username is not available");
        }

        model.addAttribute("signupDto", new SignupDto());
        return "signup";
    }
}
