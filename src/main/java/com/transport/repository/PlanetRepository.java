package com.transport.repository;

import com.transport.orm.Planet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PlanetRepository extends JpaRepository<Planet, String> {

}
