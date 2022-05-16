package dev.asgui.address.client;

import dev.asgui.address.dto.ResponseConsultCepDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ConsultCepClient {

    @Value("${cep.url}")
    private String url;

    private static final String PATH = "/ws/";

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<ResponseConsultCepDto> getAddress(String cep) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString());
        headers.set(HttpHeaders.ACCEPT, MediaType.ALL_VALUE);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        var URL = url.concat(PATH).concat(cep).concat("/json/");
        return restTemplate.exchange(URL, HttpMethod.GET, entity, ResponseConsultCepDto.class);
    }
}
