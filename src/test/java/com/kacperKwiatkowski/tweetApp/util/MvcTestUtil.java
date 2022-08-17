package com.kacperKwiatkowski.tweetApp.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

public class MvcTestUtil {

    public static String asJsonStringWithDate(final Object object) {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }

    public static MockHttpServletRequestBuilder asUser(MockHttpServletRequestBuilder builder) {
        return builder.header("Authorization", "");
    }
}
