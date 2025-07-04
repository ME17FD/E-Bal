package com.e_bal.e_bal_chat_service.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.e_bal.e_bal_chat_service.models.ChatMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class chatDto {
    private Long id;

    private Long user1;
    private Long user2;
    private ChatMessage lastSeenUser1;
    private ChatMessage lastSeenUser2;
    private LocalDateTime createdAt;
    private List<ChatMessageDTO> messages;
}
