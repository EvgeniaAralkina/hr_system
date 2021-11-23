package org.example.hrService.Services;

import org.example.hrService.Models.About;
import org.example.hrService.Models.Employee;
import org.example.hrService.Repositorys.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    private MailSender mailSender;

    public Employee findById(Integer id){
        return employeeRepository.findById(id).get();
    }

    public boolean isExist(Integer id){
        return employeeRepository.existsById(id);
    }

    public List<Employee> findAll(){
        return (List<Employee>) employeeRepository.findAll();
    }

    public Map<Employee, Integer> findMed(){
        Map <Employee, Integer> employes = new HashMap<Employee, Integer>();
        Date today = new Date();
        List<Employee> emp = findAll();
        for (Employee i: emp){
            System.out.println(i.getSurname());
            Integer diff = (today.getTime()-i.getAbout().getMedicalExamination().getTime() > 13149000000L ? 1 : 0);
            employes.put(i, diff);
            System.out.println(123);
        }
        return employes;
    }

    public Employee save(Employee employee){
        return employeeRepository.save(employee);
    }

    public void delete(Integer id){
        employeeRepository.deleteById(id);
    }

    public About findAbout(Integer employee_id){
        Employee employee = findById(employee_id);
        return employee.getAbout();
    }

    public void update(Employee employee){
        employeeRepository.update(employee.getId(), employee.getName(), employee.getMiddle_name(),
                employee.getSurname(), employee.getEmail(), employee.getAge(), employee.getGender(),
                employee.getDepartment(), employee.getPosition());
    }

    public void sendEmail(Employee employee){
        String message = String.format(
                "Добрый день, %s! \n" +
                        "Вам необходимо пройти медицинкский осмотр в ближайшее время. " +
                        "Для уточнения времени и адреса обратитесь ...\n С уважением, ...",
                employee.getName() + " " + employee.getMiddle_name()
        );

        mailSender.send(employee.getEmail(), "Медицинский осмотр", message);
    }

    public void sendAllEmail(){
        List<Employee> employes = findAll();
        Date today = new Date();
        for (Employee i:employes){
            if(today.getTime()-i.getAbout().getMedicalExamination().getTime() > 13149000000L)
                sendEmail(i);

        }
    }

    public List<Employee> findAllSortedByName(){
        return employeeRepository.findAllByOrderBySurnameAsc();
    }

    public List<Employee> findAllSortedByDepartment(){
        return employeeRepository.findAllByOrderByDepartmentAsc();
    }

    public List<Employee> findAllSortedByPosition(){
        return employeeRepository.findAllByOrderByPositionAsc();
    }

    public List<Float> getSalary(Integer id){
        Employee employee = findById(id);
        List<Float> payments = new ArrayList<>();
        Float salary = employee.getAbout().getSalary();
        Float total = ((salary * 43)/57 + salary);
        payments.add(total * 8 *14);
        payments.add(salary * 8 * 14);
        payments.add(total * 0.22f * 8 * 14);
        payments.add(total * 0.029f * 8 * 14);
        payments.add(total * 0.051f * 8 * 14);
        return payments;
    }
}
