package com.richard.dbservice.repository;

import com.richard.dbservice.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuotesRepository extends JpaRepository<Quote, Integer> {

    List<Quote> findByUserName(String username);
}
