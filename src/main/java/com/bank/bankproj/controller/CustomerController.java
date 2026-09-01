package com.bank.bankproj.controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import com.bank.bankproj.entity.Customer;
import com.bank.bankproj.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/bank/customer")
public class CustomerController {
    private CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService=customerService;
    }
    @PostMapping("/create")
    public Customer createCustomer(@Valid @RequestBody Customer customer){
        return customerService.createCustomer(customer);
    }
    @GetMapping("/{id}")
    public Optional<Customer> getCustomerusingId(@PathVariable Integer id){
        return customerService.getCustomerById(id);
    }
    @GetMapping("/getAll")
    public List<Customer> getAllCustomers(){
        return customerService.findAllCustomers();
    }
    @PostMapping("/update/{Id}/{newname}")
    public Customer updateCusName(@PathVariable Integer Id,@PathVariable String newname){
        return customerService.updCusName(Id, newname);
    }
    @DeleteMapping("/delete/{Id}")
    public void deleteCustomer(@PathVariable Integer Id){
         customerService.deleteCust(Id);
    }




}
