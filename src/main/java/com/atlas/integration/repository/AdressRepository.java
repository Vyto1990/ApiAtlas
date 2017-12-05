package com.atlas.integration.repository;
import org.springframework.data.repository.CrudRepository;

import com.atlas.integration.model.Adress;


public interface AdressRepository extends CrudRepository<Adress, Long> {
}
