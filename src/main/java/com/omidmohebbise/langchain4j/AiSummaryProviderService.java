package com.omidmohebbise.langchain4j;


import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import org.springframework.stereotype.Service;

@Service
public class AiSummaryProviderService {

    private final ChatLanguageModel chatLanguageModel;


    public AiSummaryProviderService(ChatLanguageModel chatLanguageModel) {
        this.chatLanguageModel = chatLanguageModel;
    }

    public String getSummary(String content, String question) {

        System.out.println("Final request:\n" + content);
        //var finalUserRequest = new UserMessage("what is the label of energy for below ads : \n" +content;

        var finalUserRequest = new UserMessage(question + "based on the below text: \n" + content);
        return chatLanguageModel.generate(finalUserRequest).content().text();
    }
}
