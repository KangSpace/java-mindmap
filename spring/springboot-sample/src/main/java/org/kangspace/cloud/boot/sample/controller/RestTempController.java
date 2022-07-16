package org.kangspace.cloud.boot.sample.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * RestTempController
 * @author kango2gler@gmail.com
 */
@Slf4j
@RestController
public class RestTempController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/rest")
    public String testRestRequest() {
        String url = "https://kangspace.org";
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(multiValueMap,httpHeaders);
        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET,request,String.class);
        log.info(result.toString());
        return "OK";
    }
}
