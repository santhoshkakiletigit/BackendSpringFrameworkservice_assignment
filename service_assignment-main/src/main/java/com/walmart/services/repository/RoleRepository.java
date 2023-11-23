package com.walmart.services.repository;

import com.walmart.services.models.ERole;
import com.walmart.services.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
  Optional<Role> findByName(ERole name);
}
