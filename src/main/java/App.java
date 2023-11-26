import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Hello world!
 */
public class App {
    public static String removeHTMLTags(String input) {
        return input.replaceAll("<[^>]+>", ""); // Removes HTML tags using regular expressions
    }

    public static String removeHeadContent(String html) {
        return html.replaceAll("<head[^>]*>.*?</head>", ""); // Removes content between <head> and </head>
    }

    public static String removeStyleContent(String html) {
        return html.replaceAll("<style[^>]*>.*?</style>", ""); // Removes content between <head> and </head>
    }

    public static String removeScriptContent(String html) {
        return html.replaceAll("<script[^>]*>.*?</style>", ""); // Removes content between <head> and </head>
    }

    public static String removeImageContent(String html) {
        return html.replaceAll("<img * />", ""); // Removes content between <head> and </head>
    }


    public static void main(String[] args) throws InterruptedException {
        String content = removeHeadContent(readData());
        content = removeStyleContent(content);
        content = removeScriptContent(content);
        content = removeHTMLTags(content);
        content = removeImageContent(content);
        String[] contentArr = convertToArrays(content);
        // Create a URL object
        //content = content.substring(index,index + 2000);
        // Create an instance of a model
        for (String str : contentArr) {
            ChatLanguageModel model = OpenAiChatModel.withApiKey(ApiKeys.OPENAI_API_KEY);

            // Start interacting
            String answer = model.generate("give me number of rooms: \n" + content);

            System.out.println(answer); // Hello! How can I assist you today?
            Thread.sleep(1000);
        }
    }

    private static String[] convertToArrays(String content) {
        int block = 4000;
        String[] arr = new String[(content.length() / 4000) + 1];
        for (int i = 0; i < arr.length - 1; i++) {

            arr[i] = content.substring(i * block, (i * block) + block);
        }
        return arr;
    }

    private static String readData() {
        String url = "https://www.finn.no/realestate/homes/ad.html?finnkode=323977497";

        try {
            // Create a URL object
            URL website = new URL(url);

            // Open a connection to the URL
            BufferedReader reader = new BufferedReader(new InputStreamReader(website.openStream()));

            // Read the data into a StringBuilder
            StringBuilder content = new StringBuilder();
            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                content.append(inputLine);
            }

            // Close the reader
            reader.close();

            // Print or use the content as needed
            return content.toString();


        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }
}
