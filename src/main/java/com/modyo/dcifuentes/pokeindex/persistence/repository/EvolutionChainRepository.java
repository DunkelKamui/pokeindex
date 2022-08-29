package com.modyo.dcifuentes.pokeindex.persistence.repository;

import com.modyo.dcifuentes.pokeindex.persistence.entity.EvolutionChain;
import com.modyo.dcifuentes.pokeindex.persistence.entity.Resource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class EvolutionChainRepository implements IEvolutionChainRepository{

    @Override
    public EvolutionChain findByResource(Resource resource) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<EvolutionChain> responseEntity =
                restTemplate.exchange(
                        resource.getUrl(),
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<EvolutionChain>() {}
                );
        EvolutionChain response = responseEntity.getBody();
        return responseEntity.getBody();
    }
}
