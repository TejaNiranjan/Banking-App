package com.teja.banking.service.impl;

import com.teja.banking.dto.AccountDto;
import com.teja.banking.entity.Account;
import com.teja.banking.exception.AccountNotFoundException;
import com.teja.banking.exception.InsufficientBalanceEception;
import com.teja.banking.mapper.AccountMapper;
import com.teja.banking.repository.AccountRepository;
import com.teja.banking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account= AccountMapper.mapToAccount(accountDto);
        Account a=accountRepository.save(account);
        return AccountMapper.mapToAccountDto(a);
    }

    @Override
    public AccountDto getAccountById(Long id) {
       Account account= accountRepository.findById(id).orElseThrow(()->new AccountNotFoundException("Account not found with the id -"+id));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, Double balance) {
       Account account= accountRepository.findById(id).orElseThrow(()->new AccountNotFoundException("Account not found with id - " +id));
      Double bal= account.getBalance() + balance;
      account.setBalance(bal);
      Account saved= accountRepository.save(account);
        return AccountMapper.mapToAccountDto(saved);
    }

    @Override
    public AccountDto withdraw(Long id, Double amount) {
        Account account= accountRepository.findById(id).orElseThrow(()->new AccountNotFoundException("Account not found with id - " +id));
        if(account.getBalance()<amount){
            throw new InsufficientBalanceEception("Insufficient Amount");
        }
        Double bal= account.getBalance() - amount;
        account.setBalance(bal);
        Account saved= accountRepository.save(account);
        return AccountMapper.mapToAccountDto(saved);

    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts= accountRepository.findAll();
        return accounts.stream().map(account -> AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {
        Account account= accountRepository.findById(id).orElseThrow(()->new AccountNotFoundException("Account not found with id - " +id));
        accountRepository.deleteById(id);
    }
}
