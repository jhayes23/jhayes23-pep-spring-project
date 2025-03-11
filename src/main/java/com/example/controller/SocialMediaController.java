package com.example.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.entity.*;


/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {


@PostMapping("/register")
public ResponseEntity<Account> register(@RequestBody Account newAccount){
    return null;
}
@PostMapping("/login")
public ResponseEntity<Account> login(@RequestBody Account LoginRequest){
    return null;
}
@PostMapping("/messages")
public ResponseEntity<Message> createMessage(@RequestBody Message message){
    return null;

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
