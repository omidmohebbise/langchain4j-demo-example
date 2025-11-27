package com.omidmohebbise.langchain4j;


import org.springframework.web.bind.annotation.*;

@RestController
public class AiController {
    private final AiSummaryProviderService aiService;
    private final HttpService httpService;

    String example = "https://www.funda.nl/detail/koop/almere/appartement-forum-77/43688289";

    public AiController(AiSummaryProviderService aiService, HttpService httpService) {
        this.aiService = aiService;
        this.httpService = httpService;
    }

    @GetMapping("/getQuestionUrl")
    public String getQuestionUrl(@RequestParam String question, @RequestParam String url) {
        return aiService.getSummary(httpService.readDataV1(url),question);
    }
}
