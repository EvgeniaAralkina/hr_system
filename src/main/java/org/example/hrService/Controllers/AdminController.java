package org.example.hrService.Controllers;

import org.example.hrService.Models.Employee;
import org.example.hrService.Models.User;
import org.example.hrService.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    @Autowired
    UserService userService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @GetMapping("/admin")
    public String registration(Principal principal, Model model) {
        model.addAttribute("user", principal.getName());

        model.addAttribute("password", userService.generatePassword());
        return "admin";
    }

    @PostMapping("/admin")
    public String addUser(User user, @RequestParam String role, Map<String, Object> model, Principal principal) {
        if (userService.save(user, role) == null)
            model.put("message", "User exists!");
        else
            model.put("message", "Пользователь успешно создан! Сообщите логин и пароль сотруднику");
        model.put ("user", principal.getName());

        return "admin";


    }
}
