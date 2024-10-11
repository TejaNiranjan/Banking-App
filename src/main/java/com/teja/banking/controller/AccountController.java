package com.teja.banking.controller;

import com.teja.banking.dto.AccountDto;
import com.teja.banking.entity.Account;
import com.teja.banking.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/bank")
public class AccountController {
    @Autowired
    private AccountServiceImpl accountService;

    @PostMapping("/create")
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto){
       AccountDto accountDto1= accountService.createAccount(accountDto);
       return new ResponseEntity<>(accountDto1, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
        AccountDto accountDto=accountService.getAccountById(id);
        return new ResponseEntity<>(accountDto,HttpStatus.OK);
    }

    @PutMapping("/deposit/{id}")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id,@RequestBody Map<String,Double> request){
       Double amount= request.get("amount");
       AccountDto amountDeposit= accountService.deposit(id,amount);
       return new ResponseEntity<>(amountDeposit,HttpStatus.OK);
    }

    @PutMapping("/withdraw/{id}")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long id,@RequestBody Map<String,Double> request){
        Double amount= request.get("amount");
        AccountDto amountwithdraw= accountService.withdraw(id,amount);
        return new ResponseEntity<>(amountwithdraw,HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<AccountDto>> getAllAccounts(){
        List<AccountDto> accountDtoList= accountService.getAllAccounts();
        return new ResponseEntity<>(accountDtoList,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account deleted successfully");
    }
}
