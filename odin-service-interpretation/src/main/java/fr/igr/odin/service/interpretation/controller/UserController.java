package fr.igr.odin.service.interpretation.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

/**
 * Created on 28/03/2019
 *
 * @author JDI
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController
public class UserController {
    @Value("${odin.environment}")
    private String environment;

    @Value("${hub.url}")
    private String hubUrl;

    @GetMapping("/environment")
    public Map<String, Object> getEnvironment() {
        JSONObject json = new JSONObject();
        json.put("environment", new JSONObject());
        json.getJSONObject("environment").put("short", environment.substring(0, 3));
        json.getJSONObject("environment").put("long", environment);
        return json.toMap();
    }

    // TODO : renvoyer un objet Hub plus complet avec les différents URLs possibles?
    @GetMapping("/hub")
    public Map hub() {
        return Collections.singletonMap("response", hubUrl);
    }

    /*@GetMapping("/user")
    public Principal user() {
        final String uri = hubUrl + "/user";
        RestTemplate restTemplate = new RestTemplate();
        Principal user = restTemplate.getForObject(uri, Principal.class);
        return user;
    }*/
}
