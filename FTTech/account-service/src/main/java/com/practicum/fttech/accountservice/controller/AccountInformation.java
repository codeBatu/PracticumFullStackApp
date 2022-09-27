package com.practicum.fttech.accountservice.controller;

import com.practicum.fttech.accountservice.model.Account;
import com.practicum.fttech.accountservice.model.Transaction;
import com.practicum.fttech.accountservice.repository.AccRepository;
import com.practicum.fttech.accountservice.services.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;

@RestController
@Slf4j
public class AccountInformation {

    private  final AccountService accountService;




    public AccountInformation(AccountService accountService) {
        this.accountService = accountService;

    }


       @PostMapping("/getAccount/{Id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long Id) {

         try {

             return ResponseEntity.ok( accountService.getAccountById(Id));
         } catch (Exception e) {
             return ResponseEntity.badRequest().build();
         }

       }
    @GetMapping("/getAllAccount")
       public  ResponseEntity<List<Account>> getAllAccounts(){
           try {
               var acc = accountService.getAllAccounts();
               System.out.println(acc);
               return ResponseEntity.ok(acc);
           } catch (Exception e) {
               return ResponseEntity.badRequest().build();
           }
       }



         public  ResponseEntity<List<Transaction>> getLastFiveTransaction(@PathVariable Long Id){
              try {
                var acc = accountService.getLastFiveTransactions(Id);
                return ResponseEntity.ok(acc);
              } catch (Exception e) {
                return ResponseEntity.badRequest().build();
              }
         }





}
