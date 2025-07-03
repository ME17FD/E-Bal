package com.e_bal.e_bal_chat_service.models;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Chat {
     @Id @GeneratedValue
    private Long id;

    private Long user1;
    private Long user2;
    
    @OneToOne
    @JoinColumn(name = "last_seen_user1_id")
    private ChatMessage lastSeenUser1;

    @OneToOne
    @JoinColumn(name = "last_seen_user2_id")
    private ChatMessage lastSeenUser2;

    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL)
    private List<ChatMessage> messages;
}
