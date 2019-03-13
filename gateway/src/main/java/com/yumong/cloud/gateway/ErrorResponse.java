package com.yumong.cloud.gateway;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

@Component
public class ErrorResponse implements ClientHttpResponse {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.BAD_REQUEST;
    }

    @Override
    public int getRawStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }

    @Override
    public String getStatusText() {
        return HttpStatus.BAD_REQUEST.name();
    }

    @Override
    public void close() {
    }

    @Override
    public InputStream getBody() throws IOException {
        HashMap<String, Object> p = new HashMap<>();
        p.put("state", HttpStatus.BAD_REQUEST.value());
        p.put("message", "Server error");
        String writeValueAsString = objectMapper.writeValueAsString(p);
        return new ByteArrayInputStream(writeValueAsString.getBytes("UTF-8"));
    }

    @Override
    public HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return headers;
    }
}