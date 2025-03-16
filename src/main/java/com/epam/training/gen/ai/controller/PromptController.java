package com.epam.training.gen.ai.controller;
import com.epam.training.gen.ai.model.PromptRequest;
import com.epam.training.gen.ai.model.PromptResponse;
import com.epam.training.gen.ai.service.PromptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prompt")
@Slf4j
public class PromptController {

    @Autowired
    private PromptService promptService;

    @PostMapping
    public PromptResponse handlePrompt(@RequestBody PromptRequest requestModel) {
        // Process the input (for now, simply return it as output).
        String input = requestModel.getInput();
        String output = promptService.getChatCompletions(input);

        // Create and return the response model with the processed output.
        return new PromptResponse(output);
    }
}