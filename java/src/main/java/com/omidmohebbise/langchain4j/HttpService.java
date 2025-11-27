package com.omidmohebbise.langchain4j;

import org.jetbrains.annotations.Nullable;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

@Service
public class HttpService {

    public String readDataV1(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            String text = doc.text();

            // Output the extracted text
            System.out.println("Extracted Text: ");
            System.out.println(text);
            return text;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String readData(String url) {
        var content = readPureHTML(url);
        if(content==null)
            throw new RuntimeException("Failed read web content");

        content = removeHeadContent(content);
        content = removeLink(content);
        content = removeStyleContent(content);
        content = removeScriptContent(content);
        content = removeHTMLTags(content);
        content = removeImageContent(content);
        return content;
    }

    @Nullable
    public static String readPureHTML(String url) {
        var content = sendHttpRequest(url);
        return content;
    }

    private static String sendHttpRequest(String url) {
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

    public static String removeLink(String html) {
        return html.replaceAll("<a[^>]*>.*?</a>", ""); // Removes content between <head> and </head>
    }

    public static String removeImageContent(String html) {
        return html.replaceAll("<img * />", ""); // Removes content between <head> and </head>
    }
}
