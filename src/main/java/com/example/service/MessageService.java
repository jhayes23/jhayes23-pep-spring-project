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
            && messageText.length() < 255
            && accountRepository.existsById(message.getPostedBy())){
                return messageRepository.save(message);
            } 
        }
        return null;
    }

    public Message getMessageById(Integer id){
        Optional<Message> optionalMessage = messageRepository.findById(id);
        return optionalMessage.isPresent() ? optionalMessage.get() : null;
    }

    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    public Integer deleteMessage(int id){
        if(messageRepository.existsById(id)){
            messageRepository.deleteById(id);
            return 1;
        }
        return null;
    }

    public Integer updateMessage(int id, Message replacement){
        if(replacement != null){
            String messageText = replacement.getMessageText();

            if(messageText != null && !messageText.isEmpty()
            && messageText.length() < 255){
                Optional<Message> optionalMessage = messageRepository.findById(id);
                if(optionalMessage.isPresent()){
                    Message message = optionalMessage.get();
                    message.setMessageText(replacement.getMessageText());
                    return messageRepository.save(message) != null ? 1 : null;
                }
            }
        }
        return null;
    }

    public List<Message> getAllMessagesByUser(int accountId){
        return messageRepository.findAllByPostedBy(accountId);
    }
}