package com.mohsen.atmservice.utils;

import com.mohsen.atmservice.controller.Token;
import com.mohsen.atmservice.exception.NotAuthenticatedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class HttpHeaderCreator {
    public static HttpHeaders create(MediaType mediaType, Token token){
        if(token==null || token.getToken()==null || token.getToken().equals("")){
            throw new NotAuthenticatedException("Not authenticated");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        headers.add("Authorization", "Bearer " + token.getToken());
        return headers;
    }
}
