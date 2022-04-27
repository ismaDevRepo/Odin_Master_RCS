package fr.igr.odin.common.dto.mapper.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

/**
 * Created on 06/06/2019
 *
 * @author JDI
 * @version 1.0.0
 * @since 1.0.0
 */
public class StringJSONConverter {
    private ObjectMapper objectMapper = new ObjectMapper();

    public Map<String, Object> toJSON(String text) {
        Map<String, Object> jsonMap = null;
        try {
            jsonMap = objectMapper.readValue(text, new TypeReference<Map<String, Object>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonMap;
    }

    public String toString(Map<String, Object> map) throws JsonProcessingException {
        return objectMapper.writeValueAsString(map);
    }
}
