package com.skepseis.demo.controllers;

import com.skepseis.model.Location;
import com.skepseis.model.Quote;
import com.skepseis.service.QuoteService;
import com.skepseis.service.impl.QuoteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/quotes")
public class QuoteController {

    @Autowired
    QuoteServiceImpl quoteService;

    @GetMapping(Path.LATEST_QUOTE)
    public Quote getQuote(){
        return quoteService.getLatestQuote();
    }

    @PostMapping(Path.NEW_QUOTE)
    public Boolean addQuote(@Valid @RequestBody Quote quote){
        return quoteService.addQuote(quote);
    }
}
