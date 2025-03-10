package com.example.tchedule.controller;

import com.example.tchedule.service.OpenAIService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
public class OpenAIController {

    private final OpenAIService openAIService;

    public OpenAIController(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }

    // 기존 POST 요청 (JSON 요청을 받을 때)
    @PostMapping
    public String chatWithAI(@RequestBody String message) {
        return openAIService.getChatResponse(message);
    }

}
