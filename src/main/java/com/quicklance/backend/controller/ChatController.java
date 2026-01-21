package com.quicklance.backend.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import com.quicklance.backend.model.ChatMessage;
import com.quicklance.backend.repository.ChatMessageRepository;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
@CrossOrigin(origins = "https://quicklance-backend-2.onrender.com")
public class ChatController {

    private final ChatMessageRepository repo;

    // Fetch conversation
    @GetMapping
    public List<ChatMessage> getChat(
        @RequestParam String from,
        @RequestParam String to
    ) {
        return repo.findBySenderEmailAndReceiverEmailOrReceiverEmailAndSenderEmailOrderByCreatedAt(
            from, to, from, to
        );
    }

    // Send message
    @PostMapping
    public ChatMessage send(@RequestBody ChatMessage msg) {
        return repo.save(msg);
    }
}
