package org.app.userservice.utils;

import jakarta.annotation.Nullable;
import org.app.userservice.Config.UserConfig;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class RestTemplateUtils {
    public UserConfig userConfig;

    public RestTemplateUtils(UserConfig userConfig) {
        this.userConfig = userConfig;
    }

    public <T> ResponseEntity<List<T>> callInterService(
            String url,
            HttpMethod method,
            @Nullable HttpEntity<?> requestEntity,
            ParameterizedTypeReference<List<T>> responseType
    ){
        RestTemplate restTemplate = userConfig.restTemplate();
        return restTemplate.exchange(url, method, requestEntity, responseType);
    }
}
