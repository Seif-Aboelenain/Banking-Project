package com.bank.bankproj.Exceptions;
//Runtime excep kan unchecked fa msh lazm  nktb gnb kol el methods ,return directly throw
public class AccountNotFoundException extends RuntimeException{

    public AccountNotFoundException(String message){
        super(message);
    }
}
