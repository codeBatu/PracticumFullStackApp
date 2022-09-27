package com.practicum.fttech.accountservice.services;


import com.practicum.fttech.accountservice.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;

@Service
public class ExchangeServices {

    AccountService accountService;

    private  final TransactionRepository transactionRepository;


    public ExchangeServices(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }
    public void buy(long id, BigDecimal amount, String currency){
        accountService.buy(id,amount,currency);
    }
    public void sell(long id, BigDecimal amount, String currency){
        accountService.sell(id,amount,currency);
    }

    public HashMap<String, BigDecimal> getExchangeRate(){
        HashMap<String, BigDecimal> exchangeRate = new HashMap<>();
        exchangeRate.put("Dollar", BigDecimal.valueOf(8.5));
        exchangeRate.put("Euro", BigDecimal.valueOf(10));
        exchangeRate.put("Gold", BigDecimal.valueOf(500));
        return exchangeRate;
    }





}
