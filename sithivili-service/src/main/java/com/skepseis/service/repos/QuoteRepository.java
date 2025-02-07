package com.skepseis.service.repos;

import com.skepseis.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository  extends JpaRepository<Quote,Integer> {
    Quote findTopByOrderByIdDesc();
}
