package com.example.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {

    @Autowired
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    // @Transactional return integer
    public Account registerAccount(Account account){
        return accountRepository.save(account);
    }

    public Account getAccountById(Integer id){
        Optional<Account> optionalAccount = accountRepository.findById(id);
        return optionalAccount.isPresent() ? optionalAccount.get() : null;
    }
}
