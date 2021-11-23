package org.example.hrService.Models;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @NotEmpty(message = "Name should not be empty")
    private String name;

    private String middle_name;

    @NotEmpty(message = "Surname should not be empty")
    private String surname;

    @Min(value = 16, message = "Age should be greater than 16")
    private int age;

    @NotEmpty(message = "gender should not be empty")
    private String gender;

    @NotEmpty(message = "Department should not be empty")
    private String department;

    @NotEmpty(message = "Position should not be empty")
    private String position;

    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    private String email;

//    @OneToOne(mappedBy = "about", cascade = CascadeType.ALL)
//    //@JoinColumn(name="about_id")
//    private About about;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    //@JoinColumn(name="about_id")
    private About about;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    //@JoinColumn(name="about_id")
    private Schedule schedule;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name="employee_id")
//    private List<Schedule> schedules;

    public Employee() {
    }

    public Employee(String name, String middle_name, String surname, int age, String gender, String department, String position, String email) {
        this.name = name;
        this.middle_name = middle_name;
        this.surname = surname;
        this.age = age;
        this.gender = gender;
        this.department = department;
        this.position = position;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public About getAbout() {
        return about;
    }

    public void setAbout(About about) {
        this.about = about;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}

