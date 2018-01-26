package com.richard.dbservice.resource;

import com.richard.dbservice.model.Quotes;
import com.richard.dbservice.service.QuotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/db")
public class DBServiceResource {

    @Autowired
    private QuotesService quotesService;

    @GetMapping("/{username}")
    public List<String> getQuotesByUserName(@PathVariable("username") final String userName) {
        return quotesService.findByUserName(userName);
    }

    @PostMapping("/add")
    public List<String> add(@RequestBody final Quotes quotes) {
        return quotesService.save(quotes);

    }

    @DeleteMapping("/{username}")
    public List<String> delete(@PathVariable("username") final String userName) {
        return quotesService.delete(userName);
    }



}
