package com.epam.training.gen.ai.config;
import com.azure.ai.openai.OpenAIAsyncClient;
import com.microsoft.semantickernel.Kernel;
import com.microsoft.semantickernel.aiservices.openai.chatcompletion.OpenAIChatCompletion;
import com.microsoft.semantickernel.orchestration.InvocationContext;
import com.microsoft.semantickernel.orchestration.InvocationReturnMode;
import com.microsoft.semantickernel.orchestration.PromptExecutionSettings;
import com.microsoft.semantickernel.orchestration.ToolCallBehavior;
import com.microsoft.semantickernel.plugin.KernelPlugin;
import com.microsoft.semantickernel.plugin.KernelPluginFactory;
import com.microsoft.semantickernel.services.chatcompletion.ChatCompletionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Map;

@Configuration
public class SemanticKernelConfiguration {

    @Bean
    public ChatCompletionService chatCompletionService(@Value("${client-azureopenai-deployment-name}") String deploymentOrModelName,
                                                       OpenAIAsyncClient openAIAsyncClient) {
        return OpenAIChatCompletion.builder()
                .build();
    }

    @Bean
    public Kernel kernel(ChatCompletionService chatCompletionService) {
        return Kernel.builder()
                .build();
    }

    @Bean
    public InvocationContext invocationContext() {
        return InvocationContext.builder()
                .build();
    }

    /**
     * Creates a map of {@link PromptExecutionSettings} for different models.
     *
     * @param deploymentOrModelName the Azure OpenAI deployment or model name
     * @return a map of model names to {@link PromptExecutionSettings}
     */
    @Bean
    public Map<String, PromptExecutionSettings> promptExecutionsSettingsMap(@Value("${client-azureopenai-deployment-name}")
                                                                            String deploymentOrModelName) {
        return Map.of(deploymentOrModelName, PromptExecutionSettings.builder()
                .build());
    }
}
