package com.teja.banking.service;

import com.teja.banking.dto.AccountDto;
import com.teja.banking.entity.Account;

import java.util.List;

public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);
    AccountDto getAccountById(Long id);
    AccountDto deposit(Long id,Double balance);
    AccountDto withdraw(Long id,Double balance);
    List<AccountDto> getAllAccounts();
    void deleteAccount(Long id);
}
