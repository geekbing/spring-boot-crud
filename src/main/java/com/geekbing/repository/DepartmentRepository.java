package com.geekbing.repository;

import com.geekbing.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by bing on 26/12/2016.
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
