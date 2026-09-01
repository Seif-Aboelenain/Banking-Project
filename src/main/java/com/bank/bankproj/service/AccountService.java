package com.bank.bankproj.service;
import org.springframework.transaction.annotation.Transactional;
import com.bank.bankproj.Exceptions.AccountNotFoundException;
import com.bank.bankproj.Exceptions.CustomerNotFoundException;
import com.bank.bankproj.Exceptions.InsufficientBalanceException;
import com.bank.bankproj.entity.Account;
import com.bank.bankproj.entity.Customer;
import com.bank.bankproj.repository.AccountRepository;
import com.bank.bankproj.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accRepository;
    private final CustomerRepository customerRepository;

    public AccountService(AccountRepository accRepository,CustomerRepository customerRepository){
        this.accRepository=accRepository;
        this.customerRepository=customerRepository;
    }
    public Account saveAcc(Account acc){
        return accRepository.save(acc);
    }
    public Account findByAccnum(Integer number){
        Account A=accRepository.findByAccnum(number);
        if (A==null)
            throw new AccountNotFoundException("Account not Found");
        return A;
    }
    public List<Account> findAll(){
        return accRepository.findAll();
    }
    public Account updateAcc(Integer accnumber,Integer balance) {
        Account account=findByAccnum(accnumber);
        account.setBalance(balance);
        return saveAcc(account);
    }
    public void deleteAcc(Integer accnumber){
        Account account=findByAccnum(accnumber);
        accRepository.delete(account);
    }
    public Account connect(Integer accId,Integer cusId){
        Optional<Account> account=accRepository.findById(accId);
        if (account.isEmpty()){
            throw new AccountNotFoundException("Account not found");
        }
        Account getAccount=account.get();
        Optional<Customer> customer=customerRepository.findById(cusId);
        if (customer.isEmpty())
            throw new CustomerNotFoundException("Customer isnt found");
        Customer getCustomer=customer.get();
        getAccount.setCustomer(getCustomer);
        saveAcc(getAccount);
        return getAccount;
    }
    public Account deposit(Integer Id, Integer amount){
          if (amount <= 0)
            throw new IllegalArgumentException("Amount must be greater than zero");
          Optional<Account> A =accRepository.findById(Id);
          if (A.isEmpty())
              throw new AccountNotFoundException("Account Does not exist");
          Account getA=A.get();
          getA.deposit(amount);
          accRepository.save(getA);
          return getA;
    }
    public Account withdraw(Integer Id,Integer amount){
        if (amount <= 0)
            throw new IllegalArgumentException("Amount must be greater than zero");
        Optional<Account> B =accRepository.findById(Id);
        if (B.isEmpty())
            throw  new AccountNotFoundException("Cany find Account");
        Account getB=B.get();
        if (amount<=getB.getBalance()){
            getB.withdraw(amount);
            accRepository.save(getB);
            return getB;
        }
        else
            throw new InsufficientBalanceException("Not enough balance");
    }
    @Transactional
    public Account transfer(Integer Id1, Integer Id2, Integer amount){
        if (amount <= 0)
            throw new IllegalArgumentException("Amount must be greater than zero");
        Optional<Account> A1 = accRepository.findById(Id1);
        Optional<Account> A2 = accRepository.findById(Id2);
        if (A1.isEmpty()|| A2.isEmpty())
            throw new AccountNotFoundException("Account doesnt exist");
        Account sourceAccount = A1.get();
        Account destAccount = A2.get();
        if (amount <= sourceAccount.getBalance()) {
            sourceAccount.withdraw(amount);
            destAccount.deposit(amount);
            accRepository.save(sourceAccount);
            accRepository.save(destAccount);
            return sourceAccount;
        }
        else
            throw new InsufficientBalanceException("Insufficient Balance");
    }
}
