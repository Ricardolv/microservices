package com.richard.stockservice.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/stock")
public class StockResource {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/{username}")
    public List<Quote> getStock(@PathVariable("username") final String userName) {

        ResponseEntity<List<String>> quoteResponse = restTemplate.exchange("http://db-service/db/" + userName, HttpMethod.GET,
                null, new ParameterizedTypeReference<List<String>>() {
                });


        List<String> quotes = quoteResponse.getBody();
        return quotes
                .stream()
                .map(quote -> {
                    Stock stock = getStockPrice(quote);
                    return new Quote(quote, null != stock.getQuote() ? stock.getQuote().getPrice() : null);
                })
                .collect(Collectors.toList());
    }

    private Stock getStockPrice(String quote) {
        try {
            return YahooFinance.get(quote);
        } catch (IOException e) {
            e.printStackTrace();
            return new Stock(quote);
        }
    }

    @Data
    @AllArgsConstructor
    private class Quote {
        private String quote;
        private BigDecimal price;
    }
}
