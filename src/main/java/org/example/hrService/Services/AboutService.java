package org.example.hrService.Services;

import org.example.hrService.Models.About;
import org.example.hrService.Models.Employee;
import org.example.hrService.Repositorys.AboutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AboutService {
    @Autowired
    AboutRepository aboutRepository;
    @Autowired
    EmployeeService employeeService;

    public int save(About about, String date, Integer employee_id){
        if (employeeService.isExist(employee_id)) {
            Employee employee = employeeService.findById(employee_id);
            about.setEmployee(employee);
            about.setMedicalExamination(parseDate(date));
            aboutRepository.save(about);
            return 0;
        }
        return 1;
    }

    public List<About> findAll(){
        return (List<About>) aboutRepository.findAll();
    }

    public About findById(Integer id){
        return aboutRepository.findById(id).get();
    }

    public Employee getEmployee(Integer id){
        return aboutRepository.findById(id).get().getEmployee();
    }

    public void update(About about, String date){
        aboutRepository.update(about.getId(), about.getSalary(), about.getStatus(), parseDate(date));
    }

    public Date parseDate(String date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsed = new Date();
        try {
            parsed = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        parsed.getDate();
        return parsed;
    }
}
