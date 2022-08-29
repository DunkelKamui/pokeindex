package com.modyo.dcifuentes.pokeindex.persistence.repository;

import com.modyo.dcifuentes.pokeindex.persistence.entity.Resource;
import com.modyo.dcifuentes.pokeindex.persistence.entity.Species;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;


@Repository
public class SpecieRepository implements ISpecieRepository{

    @Override
    public Species findByResource(Resource resource) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Species> responseEntity =
                restTemplate.exchange(
                        resource.getUrl(),
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<Species>() {}
                );
        return responseEntity.getBody();
    }
}
