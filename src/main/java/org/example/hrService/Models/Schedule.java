package org.example.hrService.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @NotEmpty(message = "field should not be empty")
    private String mon;
    @NotEmpty(message = "field should not be empty")
    private String tue;
    @NotEmpty(message = "field should not be empty")
    private String wed;
    @NotEmpty(message = "field should not be empty")
    private String thu;
    @NotEmpty(message = "field should not be empty")
    private String fri;
    @NotEmpty(message = "field should not be empty")
    private String sat;
    @NotEmpty(message = "field should not be empty")
    private String san;

    @OneToOne(cascade = {
            CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
    })
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Schedule() {
    }

    public Schedule(String mon, String tue, String wed, String thu, String fri, String sat, String san) {
        this.mon = mon;
        this.tue = tue;
        this.wed = wed;
        this.thu = thu;
        this.fri = fri;
        this.sat = sat;
        this.san = san;
    }

    public String getSan() {
        return san;
    }

    public void setSan(String san) {
        this.san = san;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMon() {
        return mon;
    }

    public void setMon(String mon) {
        this.mon = mon;
    }

    public String getTue() {
        return tue;
    }

    public void setTue(String tue) {
        this.tue = tue;
    }

    public String getWed() {
        return wed;
    }

    public void setWed(String wed) {
        this.wed = wed;
    }

    public String getThu() {
        return thu;
    }

    public void setThu(String thu) {
        this.thu = thu;
    }

    public String getFri() {
        return fri;
    }

    public void setFri(String fri) {
        this.fri = fri;
    }

    public String getSat() {
        return sat;
    }

    public void setSat(String sat) {
        this.sat = sat;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
