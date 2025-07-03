package com.e_bal.e_bal_chat_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.e_bal.e_bal_chat_service.dto.ChatMessageDTO;
import com.e_bal.e_bal_chat_service.dto.ChatResponseDTO;
import com.e_bal.e_bal_chat_service.models.Chat;
import com.e_bal.e_bal_chat_service.models.ChatMessage;
import com.e_bal.e_bal_chat_service.service.ChatService;

@RestController
@RequestMapping("/api/chats")
public class ChatController {
    @Autowired
    private ChatService chatService;

    @PostMapping
    public ResponseEntity<ChatResponseDTO> createChat(@RequestBody ChatResponseDTO chatDto) {
        Chat chat = toEntity(chatDto);
        Chat saved = chatService.saveChat(chat);
        return ResponseEntity.ok(toDto(saved));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChatResponseDTO> getChat(@PathVariable Long id) {
        return chatService.getChat(id)
                .map(this::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<ChatResponseDTO> getAllChats() {
        return chatService.getAllChats().stream().map(this::toDto).toList();
    }

    @PostMapping("/message")
    public ResponseEntity<ChatMessageDTO> sendMessage(@RequestBody ChatMessageDTO messageDto) {
        // Try to find chat between users (both directions)
        Chat chat = chatService
            .getChatBetweenUsers(messageDto.getSenderId(), messageDto.getRecipientId());
        if (chat == null) {
            chat = new Chat();
            chat.setUser1(messageDto.getSenderId());
            chat.setUser2(messageDto.getRecipientId());
            chat = chatService.saveChat(chat);
        }
        ChatMessage message = toEntity(messageDto);
        ChatMessage saved = chatService.saveMessage(message, chat);
        return ResponseEntity.ok(toDto(saved));
    }

    @GetMapping("/{chatId}/messages")
    public List<ChatMessageDTO> getMessages(@PathVariable Long chatId) {
        return chatService.getMessagesByChatId(chatId).stream().map(this::toDto).toList();
    }
    @PostMapping("/{chatId}/seen")
    public ResponseEntity<Void> updateLastSeen(@PathVariable Long chatId, @RequestParam Long userId, @RequestParam Long messageId) {
        chatService.updateLastSeenMessage(chatId, userId, messageId);
        return ResponseEntity.ok().build();
    }

    private ChatResponseDTO toDto(Chat chat) {
        String lastMessage = chat.getMessages() != null && !chat.getMessages().isEmpty() ?
            chat.getMessages().get(chat.getMessages().size() - 1).getContent() : null;
        return new ChatResponseDTO(
            chat.getId(),
            chat.getUser1(),
            chat.getUser2(),
            lastMessage
        );
    }

    private Chat toEntity(ChatResponseDTO dto) {
        Chat chat = new Chat();
        chat.setId(dto.getChatId());
        chat.setUser1(dto.getSenderId());
        chat.setUser2(dto.getRecipientId());
        // messages and createdAt are not set from DTO
        return chat;
    }

    private ChatMessageDTO toDto(ChatMessage message) {
        return new ChatMessageDTO(
            message.getChat() != null ? message.getChat().getId() : null,
            message.getSenderId(),
            message.getRecipientId(),
            message.getContent(),
            message.getTimestamp()
        );
    }

    private ChatMessage toEntity(ChatMessageDTO dto) {
        ChatMessage message = new ChatMessage();
        message.setSenderId(dto.getSenderId());
        message.setRecipientId(dto.getRecipientId());
        message.setContent(dto.getContent());
        message.setTimestamp(dto.getTimestamp());
        return message;
    }

    
} 