package com.epam.training.gen.ai.service;

import com.azure.ai.openai.OpenAIAsyncClient;
import com.azure.ai.openai.models.ChatCompletionsOptions;
import com.azure.ai.openai.models.ChatRequestUserMessage;
import com.microsoft.semantickernel.Kernel;
import com.microsoft.semantickernel.orchestration.InvocationContext;
import com.microsoft.semantickernel.services.chatcompletion.AuthorRole;
import com.microsoft.semantickernel.services.chatcompletion.ChatCompletionService;
import com.microsoft.semantickernel.services.chatcompletion.ChatHistory;
import com.microsoft.semantickernel.services.chatcompletion.ChatMessageContent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Service
@Slf4j
public class PromptService {

    @Autowired
    private OpenAIAsyncClient aiAsyncClient;

    @Value("${client-azureopenai-deployment-name}")
    private String deploymentOrModelName;

    @Autowired
    private ChatCompletionService chatCompletionService;

    @Autowired
    private Kernel kernel;

    @Autowired
    private InvocationContext invocationContext;

    public String getChatCompletions(String prompt) {
        ChatHistory history = new ChatHistory();
        history.addUserMessage(prompt);
        List<ChatMessageContent<?>> results = chatCompletionService
                .getChatMessageContentsAsync(history, kernel, invocationContext)
                .block();
        StringBuilder output = new StringBuilder();
        for (ChatMessageContent<?> result : results) {
            // Add the message from the agent to the chat history
            history.addMessage(result);
            output.append(result.getContent());
        }
        return output.toString();
    }

}
