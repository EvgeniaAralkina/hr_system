package org.example.hrService.Models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
public class About {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Min(value = 100, message = "Salary should be greater than 99")
    @Max(value = 10000, message = "Salary should be smaller than 10000")
    private Float salary;

    @NotEmpty(message = "Status should not be empty")
    private String status; //болеет, в отпуске, активен()

    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date medicalExamination;


    @OneToOne(cascade = {
            CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
    })
    @JoinColumn(name = "employee_id")
    private Employee employee;
    // smthg else



    public About() {
    }

    public About(Float salary, String status, Date medicalExamination, Employee employee) {
        this.salary = salary;
        this.status = status;
        this.medicalExamination = medicalExamination;
        this.employee = employee;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getMedicalExamination() {
        return medicalExamination;
    }

    public void setMedicalExamination(Date medicalExamination) {
        this.medicalExamination = medicalExamination;
    }
}
