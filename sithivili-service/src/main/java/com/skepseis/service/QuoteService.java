package com.skepseis.service;

import com.skepseis.model.Quote;

public interface QuoteService {
    Quote getLatestQuote();
    Boolean addQuote(Quote quote);
}
