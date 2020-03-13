package com.java.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

public class JsonConversion {
    public static String convert(Object data) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            String json = mapper.writeValueAsString(data);
            return json;
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
