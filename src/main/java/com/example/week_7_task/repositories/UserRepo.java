package com.example.week_7_task.repositories;

import com.example.week_7_task.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepo extends CrudRepository<User,Long> {
    boolean existsByEmail(String email);


    @Query( "SELECT u from User u where  u.email=?1 and upper(u.password) = upper(?2)")
    Optional<User> findByEmailAndPassword(String email, String password);


}
