package com.e_bal.e_bal_chat_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_bal.e_bal_chat_service.models.Chat;
import com.e_bal.e_bal_chat_service.models.ChatMessage;
import com.e_bal.e_bal_chat_service.repository.ChatMessageRepository;
import com.e_bal.e_bal_chat_service.repository.ChatRepository;

@Service
public class ChatService {
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private ChatMessageRepository chatMessageRepository;

    public Chat saveChat(Chat chat) {
        return chatRepository.save(chat);
    }

    public Optional<Chat> getChat(Long id) {
        return chatRepository.findById(id);
    }

    public List<Chat> getAllChats() {
        return chatRepository.findAll();
    }

    public ChatMessage saveMessage(ChatMessage message, Chat chat) {
        message.setChat(chat);
        return chatMessageRepository.save(message);
    }

    public List<ChatMessage> getMessagesByChatId(Long chatId) {
        return chatMessageRepository.findByChat_Id(chatId);
    }

    public Chat getChatBetweenUsers(Long user1, Long user2) {
        Chat chat = chatRepository.findByUser1AndUser2(user1, user2);
        if (chat == null) {
            chat = chatRepository.findByUser1AndUser2(user2, user1);
        }
        return chat;
    }
} 