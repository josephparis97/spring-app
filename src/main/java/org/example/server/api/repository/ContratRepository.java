package org.example.server.api.repository;

import java.util.Optional;

import org.example.server.model.Contrat;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ContratRepository extends PagingAndSortingRepository<Contrat, Long> {

	Optional<Contrat> findById(Long id);

	

}
