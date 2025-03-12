package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.context.ApplicationContext;

import com.example.SocialMediaApp;
import com.example.entity.*;
import com.example.service.AccountService;
import com.example.service.MessageService;


/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {

@Autowired
private AccountService accountService;

@Autowired
private MessageService messageService;

//TODO FIX REGISTER
@PostMapping("/register")
public ResponseEntity<Account> register(@RequestBody Account newAccount){
    try {
       Account account = accountService.registerAccount(newAccount);
       return ResponseEntity.status(200).body(account);
    } catch (IllegalArgumentException e) {
        return ResponseEntity.status(409).body(null);
    }
    
}
@PostMapping("/login")
public ResponseEntity<Account> login(@RequestBody Account LoginRequest){
    Account login = accountService.loginAccount(LoginRequest);
    if(login == null) return ResponseEntity.status(401).body(null);
    return ResponseEntity.status(200).body(login);
}
@PostMapping("/messages")
public ResponseEntity<Message> createMessage(@RequestBody Message message){
    Message createMessage = messageService.createMessage(message);
    if(createMessage == null) return ResponseEntity.status(400).body(null);
    return ResponseEntity.status(200).body(createMessage);

}
@GetMapping("messages")
public ResponseEntity<List<Message>> allMessages(){
    return null;

}
@GetMapping("/messages/{messageId}")
public ResponseEntity<Message> getMessage(@PathVariable Long messageId){
    return null;
}
@DeleteMapping("/messages/{messageId}")
public ResponseEntity<String> deleteMessage(@PathVariable Long messageId){
    return null;
}
@PatchMapping("/messages/{messageId}")
public ResponseEntity<String> updateMessage(@PathVariable Long messageId){
    return null;
}
@GetMapping("accounts/{accountId}/messages")
public ResponseEntity<Message> getUserMessage(@PathVariable long userId){
    return null;
}
}
