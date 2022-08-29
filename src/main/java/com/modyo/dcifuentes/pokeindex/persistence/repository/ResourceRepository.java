package com.modyo.dcifuentes.pokeindex.persistence.repository;

import com.modyo.dcifuentes.pokeindex.persistence.entity.Resource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;

@Repository
public class ResourceRepository implements IResourceRepository<LinkedHashMap<String,Object>>{
    @Override
    public LinkedHashMap<String,Object> findByResource(Resource resource) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<LinkedHashMap<String,Object>> responseEntity =
                restTemplate.exchange(
                        resource.getUrl(),
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<LinkedHashMap<String,Object>>() {}
                );
        return responseEntity.getBody();
    }
}
