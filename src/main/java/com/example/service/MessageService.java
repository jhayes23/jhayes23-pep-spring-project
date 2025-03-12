package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;


@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private AccountRepository accountRepository;

    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    public Message createMessage(Message message){
        if(message != null){
            String messageText = message.getMessageText();

            if(messageText != null && !messageText.isEmpty()
            && messageText.length() < 255){
                return null;
            } 
        }
        return messageRepository.save(message);
    }

    public Message getMessageById(Integer id){
        Optional<Message> optionalMessage = messageRepository.findById(id);
        return optionalMessage.isPresent() ? optionalMessage.get() : null;
    }

    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    public void deleteMessage(Integer id){
        messageRepository.deleteById(id);
    }

    public Message updateMessage(Integer id, Message replacement){
        Optional<Message> optionalMessage = messageRepository.findById(id);
        if(optionalMessage.isPresent()){
            Message message = optionalMessage.get();
            message.setMessageText(replacement.getMessageText());
            return messageRepository.save(message);
        }
        return null;
    }
}

    //TODO GET BY USER



// public class UserService {

//     @Autowired
//     private UserRepository userRepository;

//     @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
//     public void updateUser(User user) {
//         // Perform database operations
//         userRepository.save(user);
//     }
