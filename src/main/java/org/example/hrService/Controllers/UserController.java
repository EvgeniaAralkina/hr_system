package org.example.hrService.Controllers;

import org.example.hrService.Models.About;
import org.example.hrService.Models.Role;
import org.example.hrService.Models.User;
import org.example.hrService.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @GetMapping("/myaccount")
    public String userPageWithDog(Principal principal,Model model) {

        User user = userService.findByName(principal.getName());
        if (user.getRoles().contains(Role.ADMIN))
            return "redirect:/admin";
        model.addAttribute("user", user);
        return "lku";
    }
}
