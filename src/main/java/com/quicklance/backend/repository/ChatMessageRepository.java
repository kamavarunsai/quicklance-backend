package com.quicklance.backend.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.quicklance.backend.model.ChatMessage;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    List<ChatMessage> findBySenderEmailAndReceiverEmailOrReceiverEmailAndSenderEmailOrderByCreatedAt(
        String sender1,
        String receiver1,
        String sender2,
        String receiver2
    );
}
