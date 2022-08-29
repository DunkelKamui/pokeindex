package com.modyo.dcifuentes.pokeindex.services;

import com.modyo.dcifuentes.pokeindex.persistence.entity.*;
import com.modyo.dcifuentes.pokeindex.persistence.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class PokemonServiceImpl implements IPokemonService{

    IPokemonRepository pokemonRepository;
    ISpecieRepository speciesRepository;
    IEvolutionChainRepository evolutionChainRepository;

    @Autowired
    public PokemonServiceImpl (ApplicationContext applicationContext) {
        pokemonRepository = applicationContext.getBean(PokemonRepository.class);
        speciesRepository = applicationContext.getBean(SpecieRepository.class);
        evolutionChainRepository = applicationContext.getBean(EvolutionChainRepository.class);
    }

    @Override
    public PokemonList findAll(int page, int size) {
        return pokemonRepository.findAll(page,size);
    }

    @Override
    public Pokemon findById(Long id) {
        return pokemonRepository.findById(id);
    }

    @Override
    public Pokemon findPokemonByResource(Resource resource) {
        return pokemonRepository.findByResource(resource);
    }

    @Override
    public EvolutionChain findEvolutionByResource(Resource resource) {
        return evolutionChainRepository.findByResource(resource);
    }

    @Override
    public Species findSpeciesByResource(Resource resource) {
        return speciesRepository.findByResource(resource);
    }
}
