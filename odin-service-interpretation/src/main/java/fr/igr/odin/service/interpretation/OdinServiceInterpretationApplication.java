package fr.igr.odin.service.interpretation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(exclude = JmxAutoConfiguration.class)
@EntityScan("fr.igr.odin.common.model")
public class OdinServiceInterpretationApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(OdinServiceInterpretationApplication.class, args);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("*");
    }
}
