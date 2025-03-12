package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.entity.*;
import com.example.service.AccountService;
import com.example.service.MessageService;


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
    List<Message> allMessages = messageService.getAllMessages();
    return ResponseEntity.status(200).body(allMessages);

}
@GetMapping("/messages/{messageId}")
public ResponseEntity<Message> getMessage(@PathVariable Integer messageId){
    Message message = messageService.getMessageById(messageId);
    return ResponseEntity.status(200).body(message);
}
@DeleteMapping("/messages/{messageId}")
public ResponseEntity<Integer> deleteMessage(@PathVariable Integer messageId){
    Integer deletedEntry = messageService.deleteMessage(messageId);
    return ResponseEntity.status(200).body(deletedEntry);
}
@PatchMapping("/messages/{messageId}")
public ResponseEntity<Integer> updateMessage(@PathVariable Integer messageId, @RequestBody Message message){
    Integer updatedEntry = messageService.updateMessage(messageId, message);
    if(updatedEntry == null) return ResponseEntity.status(400).body(updatedEntry);
    return ResponseEntity.status(200).body(updatedEntry);
}
@GetMapping("accounts/{accountId}/messages")
public ResponseEntity<List<Message>> getUserMessage(@PathVariable Integer accountId){
    List<Message> allMessages = messageService.getAllMessagesByUser(accountId);
    return ResponseEntity.status(200).body(allMessages);
}
}
