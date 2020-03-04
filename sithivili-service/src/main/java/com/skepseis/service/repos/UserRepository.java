package com.skepseis.service.repos;


import com.skepseis.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    List<com.skepseis.model.Volunteer> findAllByUsertype(String userType);

    com.skepseis.model.User findByUsernameAndUsertype(String username, String usertype);

    com.skepseis.model.User findByUsername(String username);

    com.skepseis.model.User findByUsernameAndPassword(String username, String password);



}
