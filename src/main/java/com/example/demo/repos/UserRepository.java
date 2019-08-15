package com.example.demo.repos;

import com.example.demo.model.Admin;
import com.example.demo.model.Client;
import com.example.demo.model.User;
import com.example.demo.model.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    @Query("select u from User u where u.usertype = 'Volunteer'")
    List<Volunteer> findAllVolunteers();

    @Query("select u from User u where u.username = :username")
    Client findClientByName(String username);

    @Query("select u from User u where u.username = :username")
    Volunteer findVolunteerByName(String username);

    @Query("select u from User u where u.username = :username")
    Admin findAdminByName(String username);
}
