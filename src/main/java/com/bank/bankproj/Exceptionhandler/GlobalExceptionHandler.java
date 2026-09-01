package com.bank.bankproj.Exceptionhandler;


import com.bank.bankproj.Exceptions.AccountNotFoundException;
import com.bank.bankproj.Exceptions.CustomerNotFoundException;
import com.bank.bankproj.Exceptions.InsufficientBalanceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<String> handleAccountNotFound(AccountNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<String> handleCustomerNotFound(CustomerNotFoundException ce){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ce.getMessage());
    }
    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<String> handleInsufficientBalance(InsufficientBalanceException ie){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ie.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationparameters(MethodArgumentNotValidException ex){
        var errors=ex.getBindingResult().getFieldErrors();
        String bodymessage="";
        for(int i=0;i<errors.size();i++){
            var error=errors.get(i);
            String field=error.getField();
            String message=error.getDefaultMessage();
            bodymessage+=field + ": " + message +"\n";
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bodymessage);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }



}
