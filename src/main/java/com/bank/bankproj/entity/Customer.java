package com.bank.bankproj.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
public class Customer {
    @Id
    @GeneratedValue
    private int id;
    @NotBlank
    private String name;
    @NotBlank
    @Email
    private String email;
    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<Account> accounts;

    public Customer(){
    }
    public Customer(String name,String email){
        this.name=name;
        this.email=email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
