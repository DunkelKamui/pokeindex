package com.modyo.dcifuentes.pokeindex.persistence.repository;

import com.modyo.dcifuentes.pokeindex.persistence.entity.Resource;

public interface IResourceRepository<T> {
    T findByResource(Resource resource);
}
