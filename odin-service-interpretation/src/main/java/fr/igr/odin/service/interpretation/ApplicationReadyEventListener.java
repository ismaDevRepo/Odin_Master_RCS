package fr.igr.odin.service.interpretation;

import org.modelmapper.ModelMapper;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationReadyEventListener implements ApplicationListener<ApplicationReadyEvent> {
    public static ModelMapper modelMapper;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent applicationReadyEvent) {
        modelMapper = new ModelMapper();
    }
}
