package zjc.ai;

import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.chat.response.StreamingChatResponseHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

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

    /**
     * 流式输出技术能够实现"边生成边展示"的效果，大幅降低感知延迟
     * 重点是webflux以及返回的形式设置text/event-stream很重要
     * 流式输出可以看控制台
     * @param prompt
     * @return
     */
    @GetMapping(value = "/stream",produces = "text/event-stream")
    public Flux<String> stream(@RequestParam String prompt) {
        return Flux.create(sink -> {
            ollama.ollamaStreamingChatModel().chat(prompt, new StreamingChatResponseHandler() {
                @Override
                public void onPartialResponse(String partialResponse) {
                    // 按照token输出
                    sink.next(partialResponse);
                }

                @Override
                public void onCompleteResponse(ChatResponse completeResponse) {
                    sink.complete();
                }

                @Override
                public void onError(Throwable error) {
                    sink.error(error);
                }
            });
        });
    }
}
