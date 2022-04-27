package fr.igr.odin.service.interpretation.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created on 29/03/2019
 *
 * @author JDI
 * @version 1.0.0
 * @since 1.0.0
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Value("${hub.url}")
    private String hubUrl;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        //This is used by the oauth2 library as a unique identifier of your microservice.
        // It is used to verify that your service is the intended audience of a given JWT access token.
        //resources.resourceId("me");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //The scopes specified here should begin with your resourceId from above.
        http.authorizeRequests()
                //.antMatchers(HttpMethod.GET, "/odin-service-interpretation/**").access("#oauth2.hasScope('read')")
                //.antMatchers(HttpMethod.POST, "/api/v1/person").access("#oauth2.hasScope('users.write')")
                .antMatchers(HttpMethod.GET, "/hub").anonymous()//.access("#oauth2.hasScope('read')")
                .antMatchers(HttpMethod.GET, "/**").access("#oauth2.hasScope('read')")
                .antMatchers(HttpMethod.POST, "/**").access("#oauth2.hasScope('write')")
                .antMatchers(HttpMethod.PATCH, "/**").access("#oauth2.hasScope('write')")
                .antMatchers(HttpMethod.PUT, "/**").access("#oauth2.hasScope('write')")
                .antMatchers(HttpMethod.DELETE, "/**").access("#oauth2.hasScope('write')")
                /*.and()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint())*/;
    }

    private AuthenticationEntryPoint authenticationEntryPoint() {
        return new AuthenticationEntryPoint() {
            // You can use a lambda here
            @Override
            public void commence(HttpServletRequest aRequest, HttpServletResponse aResponse,
                                 AuthenticationException aAuthException) throws IOException {
                aResponse.sendRedirect(hubUrl + "/oauth/authorize");
            }
        };
    }
}
