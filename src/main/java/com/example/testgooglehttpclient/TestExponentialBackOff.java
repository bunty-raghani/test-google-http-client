package com.example.testgooglehttpclient;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpBackOffUnsuccessfulResponseHandler;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.util.ExponentialBackOff;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TestExponentialBackOff {

    void testError(int errorCode) throws IOException {
        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();

        HttpRequest request = requestFactory.buildGetRequest(new GenericUrl("https://mock.codes/"+errorCode));
        request.setUnsuccessfulResponseHandler(new HttpBackOffUnsuccessfulResponseHandler(new ExponentialBackOff()));
        request.setReadTimeout(0);
        request.setNumberOfRetries(3);

        long startTime = System.nanoTime();
        try {
            String rawResponse = request.execute().parseAsString();
            System.out.println("Response: " + rawResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long endTime = System.nanoTime();

        long duration = (endTime - startTime) / 1000000000;
        System.out.println("Total duration: " + duration + " seconds");
    }
}

