package com.skepseis.service.repos;

import com.skepseis.model.VolunteerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolunteerDetailsRepository  extends JpaRepository<VolunteerDetails,Integer> {
}
