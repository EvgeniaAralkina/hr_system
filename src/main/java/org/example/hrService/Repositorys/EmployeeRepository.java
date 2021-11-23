package org.example.hrService.Repositorys;


import org.example.hrService.Models.Employee;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends CrudRepository <Employee, Integer> {
    @Modifying
    @Query("update Employee a set a.name = :name, a.middle_name = :middle_name, a.surname = :surname," +
            "a.age = :age, a.gender = :gender, a.department = :department," +
            "a.position = :position, a.email = :email where a.id = :id")
    void update(@Param(value = "id") Integer id, @Param(value = "name") String name,
                @Param(value = "middle_name") String middle_name, @Param(value = "surname") String surname,
                @Param(value = "email") String email, @Param(value = "age") int age,
                @Param(value = "gender") String gender, @Param(value = "department") String department,
                @Param(value = "position") String position);

    public List<Employee> findAllByOrderBySurnameAsc();
    public List<Employee> findAllByOrderByDepartmentAsc();
    public List<Employee> findAllByOrderByPositionAsc();
}
