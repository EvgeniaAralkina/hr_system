package org.example.hrService.Controllers;

import org.example.hrService.Models.About;
import org.example.hrService.Models.Employee;
import org.example.hrService.Models.Schedule;
import org.example.hrService.Services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    ScheduleService scheduleService;

    @GetMapping()
    public String edit(Model model) {
        model.addAttribute("schedule", scheduleService.findAll());
        model.addAttribute("total", scheduleService.totalWorkingHours());
        return "schedule";
    }

    @PostMapping()
    public String sort(Model model, @RequestParam String sort) {
        switch (sort){
            case "name":
                model.addAttribute("schedule", scheduleService.findAllSortedByName());
                break;
            case "dep":
                model.addAttribute("schedule", scheduleService.findAllSortedByPosition());
                break;
            case "pos":
                model.addAttribute("schedule", scheduleService.findAllSortedByDepartment());
                break;
        }
        model.addAttribute("total", scheduleService.totalWorkingHours());
        return "schedule";
    }

    @GetMapping("/new")
    public String newSchedule(@RequestParam Integer id, Model model, @ModelAttribute("schedule") Schedule schedule) {
        model.addAttribute("id", id);
        return "schedule_new";
    }

    @PostMapping("/new")
    public String createEmployee(@RequestParam Integer id,
                                 @ModelAttribute("schedule") @Valid Schedule schedule,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/schedule/new?id=" + id;
        }
        scheduleService.save(schedule, id);
        return "redirect:/employee";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("schedule", scheduleService.findByEmployeeId(id));
        model.addAttribute("id", id);
        return "schedule_update";
    }

    @PatchMapping("/edit")
    public String update(@ModelAttribute("schedule") @Valid Schedule schedule,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "schedule_update";
        scheduleService.update(schedule);
        return "redirect:/employee";
    }
}
