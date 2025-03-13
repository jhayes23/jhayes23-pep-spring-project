package com.example.service;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.exception.InvalidCredentialsException;
import com.example.repository.AccountRepository;

@Service
public class AccountService {

    @Autowired
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public Account registerAccount(Account account)throws IllegalArgumentException, InvalidCredentialsException{
     
            if(account == null || account.getUsername() == null 
            || account.getPassword().length() < 4
            || account.getPassword() == null 
            || account.getUsername().trim().isEmpty()) throw new InvalidCredentialsException("Does not meet registration requirements.");
            

            if(getAccountByUser(account.getUsername()).isPresent()) {
                throw new IllegalArgumentException("Username already exists.");
            }
       

        return accountRepository.save(account);
    }

    public Optional<Account> getAccountByUser(String username){
        return accountRepository.findByUsername(username);
    }

    public Account loginAccount(Account account){
        Optional<Account> optionalAccount = accountRepository.findByUsernameAndPassword
        (account.getUsername(),account.getPassword());
        if (optionalAccount.isPresent()) {
            return optionalAccount.get();
        }
       return null;
    }
}
