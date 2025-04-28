package zjc.ai;

import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.chat.response.ChatResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/ollama")
public class OllamaController {
    @Resource
    OllamaConfig ollama;

    @GetMapping(value ="/chat")
    public String chat(@RequestParam(value = "msg",defaultValue = "你好") String msg){
        ChatRequest request = ChatRequest.builder()
                .messages(UserMessage.userMessage(TextContent.from(msg)))
                .build();
        ChatResponse chat = ollama.ollamaChatModel().chat(request);
        return chat.aiMessage().text();
    }
}
