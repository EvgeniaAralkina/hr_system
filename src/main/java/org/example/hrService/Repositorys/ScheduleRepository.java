package org.example.hrService.Repositorys;

import org.example.hrService.Models.Employee;
import org.example.hrService.Models.Schedule;
import org.example.hrService.Models.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScheduleRepository extends CrudRepository<Schedule, Integer> {
    @Modifying
    @Query("update Schedule a set a.mon = :mon, a.tue = :tue," +
            "a.wed = :wed, a.thu = :thu, a.fri = :fri, a.sat = :sat, a.san = :san where a.id = :id")
    void update(@Param(value = "id") Integer id, @Param(value = "mon") String mon,
                @Param(value = "tue") String tue, @Param(value = "wed") String wed,
                @Param(value = "thu") String thu, @Param(value = "fri") String fri,
                @Param(value = "sat") String sat, @Param(value = "san") String san);

}
