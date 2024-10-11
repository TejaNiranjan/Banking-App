package com.teja.banking.mapper;

import com.teja.banking.dto.AccountDto;
import com.teja.banking.entity.Account;

public class AccountMapper {

    public static AccountDto mapToAccountDto(Account account){
        return new AccountDto(account.getId(),account.getAccountHolderName(),account.getBalance());
    }

    public  static Account mapToAccount(AccountDto accountDto){
        return  new Account(accountDto.getId(),accountDto.getAccountHolderName(),accountDto.getBalance());
    }
}
