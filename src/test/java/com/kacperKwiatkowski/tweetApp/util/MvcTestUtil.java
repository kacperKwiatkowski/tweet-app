package com.kacperKwiatkowski.tweetApp.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.Gson;

public class MvcTestUtil {

    public static String asJsonStringWithDate(final Object object) {

//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.registerModule(new JavaTimeModule());

        Gson gson = new Gson();

        String s = gson.toJson(object);

        return s;
    }
}
