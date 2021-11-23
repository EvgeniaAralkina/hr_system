package org.example.hrService.Controllers;


import org.example.hrService.Models.About;
import org.example.hrService.Models.Employee;
import org.example.hrService.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("employee", employeeService.findAll());
        return "employee_index";
    }

    @PostMapping()
    public String sort(Model model, @RequestParam String sort) {
        switch (sort){
            case "name":
                model.addAttribute("employee", employeeService.findAllSortedByName());
                break;
            case "dep":
                model.addAttribute("employee", employeeService.findAllSortedByPosition());
                break;
            case "pos":
                model.addAttribute("employee", employeeService.findAllSortedByDepartment());
                break;

        }
        return "employee_index";
    }

    @GetMapping("/{id}")
    public String showById(@PathVariable("id") Integer id, Model model) {
        System.out.println("in get id");
        About about = employeeService.findAbout(id);

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        model.addAttribute("data", format.format(about.getMedicalExamination()));

        model.addAttribute("about", about);
        return "about_one";
    }

    @GetMapping("/new")
    public String newEmployee(@ModelAttribute("employee") Employee employee) {
        return "employee_new";
    }

    @PostMapping("/new")
    public String createEmployee(@ModelAttribute("employee") @Valid Employee employee,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "employee_new";

        Integer id = employeeService.save(employee).getId();

        return ("redirect:/about/new?id=" + id+"&error=\"\"");
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Integer id) {
        System.out.println("deleteMapping");
        employeeService.delete(id);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("employee", employeeService.findById(id));
        return "employee_update";
    }

    @PatchMapping("/edit")
    public String update(@ModelAttribute("employee") @Valid Employee employee,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "employee_update";
        System.out.println(employee.getPosition());
        employeeService.update(employee);
        //return ("redirect:/about/update&error=\"\"");
        return "redirect:/employee";
    }

    @GetMapping("/{id}/salary")
    public String salary(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("payments", employeeService.getSalary(id));
        return "salary";
    }
}
