package org.example.hrService.Controllers;

import org.example.hrService.Models.About;
import org.example.hrService.Repositorys.AboutRepository;
import org.example.hrService.Repositorys.EmployeeRepository;
import org.example.hrService.Services.AboutService;
import org.example.hrService.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/about")
public class AboutController {

    @Autowired
    private AboutService aboutService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("about", aboutService.findAll());
        return "about_index";
    }

    @GetMapping("/new")
    public String newAbout(@RequestParam Integer id, @RequestParam String error, Model model,
                           @ModelAttribute("about") About about) {
        model.addAttribute("id", id);
        System.out.println(error);
        if (error.equals("Medical examination should not be empty")) {
            System.out.println("in if get new about");
            model.addAttribute("error", error);
        }
        return "about_new";
    }

    @PostMapping("/new")
    public String createAbout(@RequestParam Integer id, @RequestParam String date,
                              @ModelAttribute("about") @Valid  About about, BindingResult bindingResult) {
        System.out.println(date);
        if (bindingResult.hasErrors() || date.equals("")) {
            return "redirect:/about/new?id=" + id+"&error=Medical examination should not be empty";
        }

        int is_create = aboutService.save(about, date, id);
        if (is_create !=1){
            return "redirect:/schedule/new?id=" + id;
        }
        return "_error";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id, @RequestParam String error) {
        if (error.equals("Medical examination should not be empty")) {
            System.out.println("in if get edit about");
            model.addAttribute("error", error);
        }
        model.addAttribute("about", aboutService.findById(id));
        return "about_update";
    }

    @PatchMapping("/edit")
    public String update(@RequestParam Integer id,@RequestParam String date, @ModelAttribute("about") @Valid About about, BindingResult bindingResult) {
        if (bindingResult.hasErrors() || date.equals("")) {
            return "redirect:/about/" + id + "/edit?error=Medical examination should not be empty";
        }
        System.out.println("in update " + about.getId() + " " + about.getSalary());
        aboutService.update(about, date);
        return "redirect:/employee";
    }
}
