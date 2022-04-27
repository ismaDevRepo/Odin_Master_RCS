package fr.igr.odin.common.dto.mapper.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.AbstractConverter;

import java.io.IOException;
import java.util.Map;

/**
 * Created on 16/06/2019
 *
 * @author JDI
 * @version 1.0.0
 * @since 1.0.0
 */
public class JSONToStringConverter extends AbstractConverter<String, Map<String, Object>> {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected Map<String, Object> convert(String s) {
        Map<String, Object> jsonMap = null;
        try {
            jsonMap = objectMapper.readValue(s, new TypeReference<Map<String, Object>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonMap;
    }
}
