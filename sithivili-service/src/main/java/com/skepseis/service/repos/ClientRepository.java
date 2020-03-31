package com.skepseis.service.repos;

import com.skepseis.model.Client;
import com.skepseis.model.User;
import com.skepseis.model.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client,Integer> {

    Client findByEmail(String email);



}
