package com.practicum.fttech.accountservice.services;


import com.practicum.fttech.accountservice.model.Account;
import com.practicum.fttech.accountservice.model.Transaction;
import com.practicum.fttech.accountservice.model.TransactionType;
import com.practicum.fttech.accountservice.repository.AccRepository;
import com.practicum.fttech.accountservice.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {


    private  final AccRepository acccountRepository;
    private final TransactionRepository transactionService;

    public AccountService(AccRepository acccountRepository, TransactionRepository transactionService) {
        this.acccountRepository = acccountRepository;
        this.transactionService = transactionService;
    }
    @PostConstruct
    private void postConstruct(){

        Account account = new Account();
        account.setUserId(1L);
        account.setTransactions(null);
        account.setTotalTurkishLira(BigDecimal.valueOf(1234121L));
        account.setTotalDollar(BigDecimal.valueOf(1234121L));
        account.setTotalEuro(BigDecimal.valueOf(1234121L));
        acccountRepository.save(account);
        Account account2 = new Account();
        account2.setUserId(2L);
        account2.setTransactions(null);
        account2.setTotalTurkishLira(BigDecimal.valueOf(1L));
        account2.setTotalDollar(BigDecimal.valueOf(1L));
        account2.setTotalEuro(BigDecimal.valueOf(1L));
        acccountRepository.save(account2);


    }

    private void  saveTransaction(Account acc , Transaction transaction, TransactionType transactionType, String currency, BigDecimal amount, String description){


        transaction.setAccounts(acc);

        transaction.setTransactionType(transactionType);
        transaction.setTransactionDescription(description + currency + " " + amount);


        transactionService.save(transaction);
    }
    //getAccount total balance
    public BigDecimal getAccountBalance(long id){
     var account =   acccountRepository.findById(id).get();
      if(account == null){
          throw new IllegalStateException("Account not found");
      }
     return  account.getTotalTurkishLira();
    }

    public Account getAccountById(long id){
        var account =   acccountRepository.findById(id).get();
        if(account == null){
            throw new IllegalStateException("Account not found");
        }
        return account;
    }

    @Transactional
    public void updateAccount(Account account){
      var acc =  acccountRepository.findById(account.getIdAcc()).get();
      if(acc.getTotalGold() != account.getTotalGold()){
          acc.setTotalGold(account.getTotalGold());
      }
      if (acc.getTotalDollar() != account.getTotalDollar()){
          acc.setTotalDollar(account.getTotalDollar());}
        if (acc.getTotalTurkishLira() != account.getTotalTurkishLira()){
            acc.setTotalTurkishLira(account.getTotalTurkishLira());}
           if (acc.getTotalEuro() != account.getTotalEuro()){
                acc.setTotalEuro(account.getTotalEuro());}


        acccountRepository.save(acc);
    }
    //buy dollars
    @Transactional
    public void buy(long id, BigDecimal amount, String currency){
        var account = getAccountById(id);

        if(account == null){
            throw new IllegalStateException("Account not found");

        }
        switch (currency){

            case "Dollar":
                account.setTotalDollar(account.getTotalDollar().add(amount));
                account.setTotalTurkishLira(account.getTotalTurkishLira().subtract(amount.multiply(BigDecimal.valueOf(8.5))));

                 saveTransaction(account,new Transaction(),TransactionType.Buy,currency,amount,"Buy ");
                break;
            case "Euro":
                account.setTotalEuro(account.getTotalEuro().add(amount));
                account.setTotalTurkishLira(account.getTotalTurkishLira().subtract(amount.multiply(BigDecimal.valueOf(10))));
                saveTransaction(account,new Transaction(),TransactionType.Buy,currency,amount,"Buy ");
                break;
            case "Gold":
                account.setTotalGold(account.getTotalGold().add(amount));
                account.setTotalTurkishLira(account.getTotalTurkishLira().subtract(amount.multiply(BigDecimal.valueOf(500))));
                saveTransaction(account,new Transaction(),TransactionType.Buy,currency,amount,"Buy ");
                break;
        }


        updateAccount(account);
    }


    @Transactional
    public  void sell (Long id,BigDecimal amount,String currency)
    {
        var account = getAccountById(id);


        if(account == null){
            throw new IllegalStateException("Account not found");
        }
        switch (currency){
            case "Dollar":
                account.setTotalDollar(account.getTotalDollar().subtract(amount));
                account.setTotalTurkishLira(account.getTotalTurkishLira().add(amount.multiply(BigDecimal.valueOf(8.5))));
                saveTransaction(account,new Transaction(),TransactionType.Sell,currency,amount,"Sell ");
                break;
            case "Euro":
                account.setTotalEuro(account.getTotalEuro().subtract(amount));
                account.setTotalTurkishLira(account.getTotalTurkishLira().add(amount.multiply(BigDecimal.valueOf(10))));
                saveTransaction(account,new Transaction(),TransactionType.Sell,currency,amount,"Sell ");
                break;
            case "Gold":
                account.setTotalGold(account.getTotalGold().subtract(amount));
                account.setTotalTurkishLira(account.getTotalTurkishLira().add(amount.multiply(BigDecimal.valueOf(500))));
                saveTransaction(account,new Transaction(),TransactionType.Sell,currency,amount,"Sell ");
                break;
        }
        updateAccount(account);
    }

    public  List<Account> getAllAccounts(){
        if(acccountRepository.findAll().isEmpty()){
            throw new IllegalStateException("No Accounts found");
        }
        return acccountRepository.findAll();
    }
    //get Last 5 transactions
    public List<Transaction> getLastFiveTransactions(long id){
        var account = getAccountById(id);
        if(account == null){
            throw new IllegalStateException("Account not found");
        }
        var trans =account.getTransactions().stream().sorted().toList();
 int si = account.getTransactions().size();
        List<Transaction> ne = new ArrayList<>();
 if(si <=5){

     for (int i = account.getTransactions().size();  5> account.getTransactions().size() ; si--) {

         ne.add(account.getTransactions().get(si));

     }
 }

// birdaha bak

        return  ne;
    }
}
