package org.example.hrService.Repositorys;

import org.example.hrService.Models.About;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface AboutRepository extends CrudRepository<About, Integer> {
    @Modifying
    @Query("update About a set a.salary = :salary, a.status = :status," +
            " a.medicalExamination = :medicalExamination where a.id = :id")
    void update(@Param(value = "id") Integer id, @Param(value = "salary") Float salary,
                @Param(value = "status") String status, @Param(value = "medicalExamination") Date medicalExamination);
}
