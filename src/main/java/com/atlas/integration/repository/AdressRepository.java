package com.atlas.integration.repository;

import org.springframework.data.repository.CrudRepository;

import com.atlas.integration.domain.Adress;


public interface AdressRepository extends CrudRepository<Adress, Long> {
}
