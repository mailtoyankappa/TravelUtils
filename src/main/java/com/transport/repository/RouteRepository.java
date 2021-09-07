package com.transport.repository;

import com.transport.orm.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RouteRepository extends JpaRepository<Route, Short>
{

}
