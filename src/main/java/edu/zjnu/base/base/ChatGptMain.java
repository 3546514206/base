package edu.zjnu.base.base;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;


/**
 * @author: 杨海波
 * @date: 2023-09-09 13:11:05
 * @description: todo
 */
public class ChatGptMain {


    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient();

        String apiKey = "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";
        String endpoint = "https://api.openai.com/v1/engines/text-davinci-003/completions";

        JSONObject requestBody = new JSONObject();
        requestBody.put("prompt", "你好");

        RequestBody body = RequestBody.create(MediaType.parse("application/json"), requestBody.toString());

        Request request = new Request.Builder()
                .url(endpoint)
                .post(body)
                .addHeader("Authorization", "Bearer " + apiKey)
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String responseData = response.body().string();
                System.out.println("Generated text: " + responseData);
            } else {
                System.err.println("API request failed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
