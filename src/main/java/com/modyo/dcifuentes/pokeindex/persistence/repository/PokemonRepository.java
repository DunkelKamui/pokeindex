package com.modyo.dcifuentes.pokeindex.persistence.repository;

import com.modyo.dcifuentes.pokeindex.persistence.entity.*;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;


@Repository
public class PokemonRepository implements IPokemonRepository {

    private static final String BASEURL = "https://pokeapi.co/api/v2/";

    @Override
    @Cacheable
    public PokemonList findAll(int page, int size)
    {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PokemonList> responseEntity =
                restTemplate.exchange(
                        BASEURL +
                        "pokemon?limit=" + size
                        + "&offset=" + page*size,

                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<PokemonList>() {}
                );
        return responseEntity.getBody();
    }

    @Override
    public Pokemon findById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Pokemon> responseEntity =
                restTemplate.exchange(
                        BASEURL + "pokemon/"+ id,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<Pokemon>() {}
                );
        return responseEntity.getBody();
    }

    @Override
    public Pokemon findByResource(Resource resource) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Pokemon> responseEntity =
                restTemplate.exchange(
                        resource.getUrl(),
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<Pokemon>() {}
                );
        return responseEntity.getBody();
    }
}
