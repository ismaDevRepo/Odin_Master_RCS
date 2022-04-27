package fr.igr.odin.common.dto.mapper.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.AbstractConverter;

import java.util.Map;

/**
 * Created on 16/06/2019
 *
 * @author JDI
 * @version 1.0.0
 * @since 1.0.0
 */
public class StringToJSONConverter extends AbstractConverter<Map<String, Object>, String> {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected String convert(Map<String, Object> stringObjectMap) {
        String s = null;
        try {
            s = objectMapper.writeValueAsString(stringObjectMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return s;
    }
}
