package fr.igr.odin.service.variant;

import fr.igr.odin.common.dto.mapper.converter.JSONToStringConverter;
import fr.igr.odin.common.dto.mapper.converter.StringToJSONConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created on 14/06/2019
 *
 * @author JDI
 * @version 1.0.0
 * @since 1.0.0
 */
@Component
public class ApplicationReadyEventListener implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    public static ModelMapper modelMapper;

    @Transactional
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        modelMapper = new ModelMapper();
        modelMapper.addConverter(new StringToJSONConverter());
        modelMapper.addConverter(new JSONToStringConverter());
    }
}
