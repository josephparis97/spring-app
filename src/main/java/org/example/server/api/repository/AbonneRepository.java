package org.example.server.api.repository;

import java.util.Optional;

import org.example.server.model.Abonne;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface AbonneRepository extends PagingAndSortingRepository<Abonne, Long> {

	Optional<Abonne> findByPrenom(@Param("prenom") String prenom);
}
