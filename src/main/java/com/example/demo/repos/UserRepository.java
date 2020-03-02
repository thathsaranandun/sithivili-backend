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

    List<Volunteer> findAllByUsertype(String userType);

    User findByUsernameAndUsertype(String username,String usertype);

    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);



}
