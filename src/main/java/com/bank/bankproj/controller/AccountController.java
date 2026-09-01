package com.bank.bankproj.controller;


import com.bank.bankproj.entity.Account;
import com.bank.bankproj.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank/account")
public class AccountController {
    private AccountService accountService;

    public AccountController(AccountService accountService ){
        this.accountService=accountService;
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/saveAcc")
    public Account saveAcc(@RequestBody Account account){
        return accountService.saveAcc(account);
    }

    @GetMapping("/{accnum}")
    public Account findByAccnum(@PathVariable int accnum){
        return accountService.findByAccnum(accnum);
    }
    @GetMapping("/AllAccounts")
    public List<Account> findAll(){
        return accountService.findAll();
    }

    @PutMapping("/update/{accnum}/{balance}")
    public Account updateAcc(@PathVariable Integer accnum,@PathVariable Integer balance){
        return accountService.updateAcc(accnum,balance);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{accnum}")
    public void deleteAcc(@PathVariable Integer accnum){
        accountService.deleteAcc(accnum);
    }
    @PutMapping("/connects/accId/{accId}/cusId/{cusId}")
    public Account connect(@PathVariable Integer accId,@PathVariable Integer cusId){
        return accountService.connect(accId,cusId);
    }
    @PutMapping("/deposit/{id}/{amount}")
    public Account deposit(@PathVariable Integer id,@PathVariable Integer amount){
        return accountService.deposit(id,amount);
    }
    @PutMapping("/withdraw/{id}/{amount}")
    public Account withdraw(@PathVariable Integer id,@PathVariable Integer amount){
        return accountService.withdraw(id,amount);
    }
    @PutMapping("/transfer/{id1}/{id2}/{amount}")
    public Account transfer(@PathVariable Integer id1,@PathVariable Integer id2,@PathVariable Integer amount){
        return accountService.transfer(id1,id2,amount);
    }








}
