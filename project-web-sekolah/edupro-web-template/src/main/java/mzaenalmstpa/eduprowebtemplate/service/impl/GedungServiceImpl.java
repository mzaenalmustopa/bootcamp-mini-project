package mzaenalmstpa.eduprowebtemplate.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import mzaenalmstpa.eduprowebtemplate.constant.BackEndUrl;
import mzaenalmstpa.eduprowebtemplate.model.request.GedungRequest;
import mzaenalmstpa.eduprowebtemplate.model.response.GedungResponse;
import mzaenalmstpa.eduprowebtemplate.model.response.Response;
import mzaenalmstpa.eduprowebtemplate.service.GedungService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GedungServiceImpl implements GedungService {

    private final BackEndUrl backEndUrl;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public List<GedungResponse> getAll() {
        try {
            var url = backEndUrl.gedungUrl();
            ResponseEntity<Response> response = restTemplate.getForEntity(url, Response.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                return (List<GedungResponse>) response.getBody().getData();
            }
        } catch (RestClientException e){
            return Collections.emptyList();
        }

        return Collections.emptyList();
    }

    @Override
    public Optional<GedungResponse> getById(String kode) {
        try {
            var url = Strings.concat(backEndUrl.gedungUrl(), "/" + kode);
            ResponseEntity<Response> response = restTemplate.getForEntity(url, Response.class);
            if (response.getStatusCode() == HttpStatus.OK){
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                GedungResponse result = objectMapper.readValue(json, GedungResponse.class);

                return Optional.of(result);
            }
        }catch (RestClientException e) {
            return Optional.empty();
        } catch (IOException e){
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    @Override
    public Optional<GedungResponse> save(GedungRequest request) {
        try {
            var url = backEndUrl.gedungUrl();
            HttpEntity<GedungRequest> httpEntity = new HttpEntity<>(request);
            ResponseEntity<Response> response = restTemplate.postForEntity(url, httpEntity, Response.class);
            if (response.getStatusCode() == HttpStatus.OK){
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                GedungResponse result = objectMapper.readValue(json, GedungResponse.class);

                return Optional.of(result);
            }
        }catch (RestClientException e){
            return Optional.empty();
        } catch (IOException e){
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    @Override
    public Optional<GedungResponse> update(GedungRequest request) {
        try {
            var url = Strings.concat(backEndUrl.gedungUrl(), "/" + request.getKode());
            HttpEntity<GedungRequest> httpEntity = new HttpEntity<>(request);
            ResponseEntity<Response> response = restTemplate.exchange(url, HttpMethod.PUT, httpEntity, Response.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                GedungResponse result = objectMapper.readValue(json, GedungResponse.class);

                return Optional.of(result);
            }
        }catch (RestClientException e){
            return Optional.empty();
        } catch (IOException e){
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    @Override
    public Optional<GedungResponse> delete(GedungRequest request) {
        try {
            var url = Strings.concat(backEndUrl.gedungUrl(), "/" + request.getKode());
            ResponseEntity<Response> response = restTemplate.exchange(url, HttpMethod.DELETE, null, Response.class);
            if (response.getStatusCode() == HttpStatus.OK){
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                GedungResponse result = objectMapper.readValue(json, GedungResponse.class);

                return Optional.empty();
            }
        }catch (RestClientException e){
            return Optional.empty();
        } catch (IOException e){
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }
}
