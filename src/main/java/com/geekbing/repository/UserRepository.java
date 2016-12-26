package com.geekbing.repository;

import com.geekbing.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by bing on 26/12/2016.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
