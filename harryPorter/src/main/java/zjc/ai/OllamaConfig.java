package zjc.ai;

import dev.langchain4j.http.client.spring.restclient.SpringRestClientBuilder;
import dev.langchain4j.model.ollama.OllamaChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class OllamaConfig {
    @Bean
    public OllamaChatModel ollamaChatModel(){
        return OllamaChatModel.builder()
                .baseUrl("http://localhost:11434")
                .modelName("deepseek-r1:7b")
                .httpClientBuilder(new SpringRestClientBuilder())
                .timeout(Duration.ofSeconds(30)) // 超时时间
                .maxRetries(3) // 超时最大重试次数
                .build();
    }
}
