package org.example.server.api.repository;

import org.example.server.model.Mouvement;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MouvementRepository extends PagingAndSortingRepository<Mouvement, Long>{

}
