package com.modyo.dcifuentes.pokeindex.persistence.repository;

import com.modyo.dcifuentes.pokeindex.persistence.entity.Pokemon;
import com.modyo.dcifuentes.pokeindex.persistence.entity.PokemonList;

public interface IPokemonRepository extends IResourceRepository<Pokemon>{
    PokemonList findAll(int page, int size);
    Pokemon findById(Long id);
}
