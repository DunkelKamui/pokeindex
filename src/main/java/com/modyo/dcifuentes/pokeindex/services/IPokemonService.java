package com.modyo.dcifuentes.pokeindex.services;

import com.modyo.dcifuentes.pokeindex.persistence.entity.*;

public interface IPokemonService {
    PokemonList findAll(int page, int size);

    Pokemon findById(Long id);

    Pokemon findPokemonByResource(Resource resource);
    EvolutionChain findEvolutionByResource(Resource resource);
    Species findSpeciesByResource(Resource resource);
}
