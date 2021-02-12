package org.sid.compteop.repositories;


import org.sid.compteop.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface OperationRepository extends JpaRepository<Operation,Long> {
}
