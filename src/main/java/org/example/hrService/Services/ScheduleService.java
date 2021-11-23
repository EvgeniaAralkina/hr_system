package org.example.hrService.Services;

import org.example.hrService.Models.Employee;
import org.example.hrService.Models.Schedule;
import org.example.hrService.Repositorys.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class ScheduleService {
    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    EmployeeService employeeService;

    public int save(Schedule schedule, Integer employee_id){
        if (employeeService.isExist(employee_id)) {
            Employee employee = employeeService.findById(employee_id);
            schedule.setEmployee(employee);
            scheduleRepository.save(schedule);
            return 0;
        }
        return 1;
    }

    public Schedule findByEmployeeId(Integer employee_id){
        Employee employee = employeeService.findById(employee_id);
        return employee.getSchedule();
    }

    public void update(Schedule sc){
        scheduleRepository.update(sc.getId(), sc.getMon(), sc.getTue(), sc.getWed(),
                sc.getThu(), sc.getFri(), sc.getSat(), sc.getSan());
    }

    public List<Schedule> findAll(){
        return (List<Schedule>) scheduleRepository.findAll();
    }

    public List<Integer> totalWorkingHours(){
        List<Schedule> schedule = findAll();
        List<Integer> hours = new ArrayList(Arrays.asList(0, 0, 0, 0, 0, 0, 0));
        for (Schedule i: schedule){
            if (i.getMon().equals("Рабочий"))
                hours.set(0, hours.get(0)+8);
            if (i.getTue().equals("Рабочий"))
                hours.set(1, hours.get(1)+8);
            if (i.getWed().equals("Рабочий"))
                hours.set(2, hours.get(2)+8);
            if (i.getThu().equals("Рабочий"))
                hours.set(3, hours.get(3)+8);
            if (i.getFri().equals("Рабочий"))
                hours.set(4, hours.get(4)+8);
            if (i.getSat().equals("Рабочий"))
                hours.set(5, hours.get(5)+8);
            if (i.getSan().equals("Рабочий"))
                hours.set(6, hours.get(6)+8);
        }
        return hours;
    }

    public List<Schedule> findAllSortedByName(){
        return sort(employeeService.findAllSortedByName());
    }

    public List<Schedule> findAllSortedByDepartment(){
        return sort(employeeService.findAllSortedByDepartment());
    }

    public List<Schedule> findAllSortedByPosition(){
        return sort(employeeService.findAllSortedByPosition());
    }

    public List<Schedule> sort(List <Employee> employees){
        List<Schedule> sc = new ArrayList<>();
        for (Employee i: employees){
            sc.add(i.getSchedule());
        }
        return sc;
    }
}
