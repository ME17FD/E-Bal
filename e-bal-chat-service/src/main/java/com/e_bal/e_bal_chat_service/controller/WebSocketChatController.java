package com.e_bal.e_bal_chat_service.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.e_bal.e_bal_chat_service.dto.ChatMessageDTO;
import com.e_bal.e_bal_chat_service.models.Chat;
import com.e_bal.e_bal_chat_service.models.ChatMessage;
import com.e_bal.e_bal_chat_service.service.ChatService;

@Controller
public class WebSocketChatController {
    @Autowired
    private ChatService chatService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload ChatMessageDTO chatMessageDTO) {
        System.out.println("Received message to send: {}"+ chatMessageDTO);
        Long senderId = chatMessageDTO.getSenderId();
        Long recipientId = chatMessageDTO.getRecipientId();
        // Find or create chat between users
        Chat chat = chatService.getChatBetweenUsers(senderId, recipientId);
        if (chat == null) {
            chat = new Chat();
            chat.setUser1(senderId);
            chat.setUser2(recipientId);
            chat = chatService.saveChat(chat);
        }
        // Create and save message
        ChatMessage message = new ChatMessage();
        message.setSenderId(senderId);
        message.setRecipientId(recipientId);
        message.setContent(chatMessageDTO.getContent());
        message.setTimestamp(LocalDateTime.now());
        message.setChat(chat);
        chatService.saveMessage(message, chat);
        // Update DTO with timestamp
        chatMessageDTO.setTimestamp(message.getTimestamp());
        // Send to sender
        messagingTemplate.convertAndSendToUser(senderId.toString(), "/queue/messages", chatMessageDTO);
        // Send to recipient
        messagingTemplate.convertAndSendToUser(recipientId.toString(), "/queue/messages", chatMessageDTO);
    }
}