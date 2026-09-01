package com.bank.bankproj.repository;

import com.bank.bankproj.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer,Integer> {

}
