package com.skepseis.service.repos;


import com.skepseis.model.Client;
import com.skepseis.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    List<com.skepseis.model.Volunteer> findAllByUsertype(String userType);

    User findByUsernameAndUsertype(String username, String usertype);

    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);



}
