package com.richard.dbservice.service;

import com.richard.dbservice.model.Quote;
import com.richard.dbservice.model.Quotes;
import com.richard.dbservice.repository.QuotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuotesService {

    @Autowired
    private QuotesRepository quotesRepository;

    public List<String> findByUserName(String userName) {
        return quotesRepository.findByUserName(userName)
                .stream()
                .map(quote -> quote.getQuote())
                .collect(Collectors.toList());
    }

    public List<String> save(Quotes quotes) {
        quotes.getQuotes()
                .stream()
                .map(quote -> new Quote(quotes.getUserName(), quote))
                .forEach(quote ->  quotesRepository.save(quote));

        return findByUserName(quotes.getUserName());
    }

    public List<String> delete(String userName) {
        List<Quote> quotes = quotesRepository.findByUserName(userName);
        quotesRepository.delete(quotes);
        return findByUserName(userName);
    }
}
