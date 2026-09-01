package com.bank.bankproj.repository;

import com.bank.bankproj.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Integer> {

    Account findByAccnum(int number);
}
