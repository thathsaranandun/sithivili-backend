package com.skepseis.service.impl;

import com.skepseis.model.Quote;
import com.skepseis.service.QuoteService;
import com.skepseis.service.repos.QuoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class QuoteServiceImpl implements QuoteService {

    @Autowired
    private QuoteRepository quoteRepository;

    @Override
    public Quote getLatestQuote() {
        try{
            return quoteRepository.findTopByOrderByIdDesc();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Boolean addQuote(Quote quote) {
        try{
            quoteRepository.save(quote);
            log.info("Quote added successfully");
            return true;
        }catch (Exception e){
            log.error("Exception occurred - {}",e);
            return false;
        }

    }
}
