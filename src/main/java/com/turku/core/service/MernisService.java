package com.turku.core.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/** @noinspection rawtypes, RedundantSuppression */
@Service
public class MernisService {

    private final RestTemplate restTemplate;

    public MernisService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /** @noinspection UnnecessaryLocalVariable*/
    public <K, T> ResponseEntity<T> callExternalService(K requestBody, String authorizationToken, String clientIp) {

        String url = "http://localhost:8100/sicilws/mernis/getNufusBilgileri";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Auth-Token", authorizationToken);
        headers.set("clientGibintraIP", clientIp);

        HttpEntity<K> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<T> exchange = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                ParameterizedTypeReference.forType(Object.class)
        );
        return exchange;
    }

    /** @noinspection divzero, NumericOverflow */
    public String hello() {
        int i = 5/0;
        return "hello world";
    }
}

