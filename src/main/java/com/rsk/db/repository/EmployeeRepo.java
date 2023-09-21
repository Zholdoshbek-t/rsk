package com.rsk.db.repository;

import com.rsk.db.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

    @Query(value = "select * from Employee where full_name like %:fullName%", nativeQuery = true)
    List<Employee> findByFullName(String fullName);
}
