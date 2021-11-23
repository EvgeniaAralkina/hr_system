package org.example.hrService.Repositorys;

import org.example.hrService.Models.Employee;
import org.example.hrService.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
    @Modifying
    @Query("update User a set a.password = :password where a.username = :username")
    void update(@Param(value = "username") String username, @Param(value = "password") String password);
    User findByUsername(String username);
}
