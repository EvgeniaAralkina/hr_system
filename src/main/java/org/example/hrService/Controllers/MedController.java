package org.example.hrService.Controllers;

import org.example.hrService.Models.About;
import org.example.hrService.Models.Employee;
import org.example.hrService.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MedController {
    @Autowired
    EmployeeService employeeService;
    String message="";

    @GetMapping("")
    public String getIndex() {
        return "index";
    }


    @GetMapping("/medExamination")
    public String getMedExamination(Model model) {
        model.addAttribute("employes", employeeService.findMed());
        if (message.equals("Письма успешно отправлены"))
            model.addAttribute("message", message);
        message = "";
        return "medExamination";
    }

    @PostMapping("/medExamination")
    public String sendEmail(Model model) {
        employeeService.sendAllEmail();
        message = "Письма успешно отправлены";
        return "redirect:/medExamination";
    }
}
