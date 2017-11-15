package com.atlas.integration.repository;

import com.atlas.integration.domain.User;

import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
