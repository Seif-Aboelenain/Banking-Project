package com.bank.bankproj.service;

import com.bank.bankproj.Exceptions.CustomerNotFoundException;
import com.bank.bankproj.entity.Customer;
import com.bank.bankproj.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository=customerRepository;
    }
    public Customer createCustomer(Customer customer){
       return  customerRepository.save(customer);
    }

    public Optional<Customer> getCustomerById(Integer Id) {
        Optional<Customer> C = customerRepository.findById(Id);
        if (C.isEmpty())
            throw new CustomerNotFoundException("Customer not found");
        return C;
    }
    public List<Customer> findAllCustomers(){
        return customerRepository.findAll();
    }
    public Customer updCusName(Integer Id,String newname){
        Optional<Customer> customer=getCustomerById(Id);
        Customer existingcustomer=customer.get();
        existingcustomer.setName(newname);
        return customerRepository.save(existingcustomer);
    }

    public void deleteCust(Integer Id) {
        Optional<Customer> customer=getCustomerById(Id);
        Customer existingcustomer=customer.get();
        customerRepository.delete(existingcustomer);
    }




}
