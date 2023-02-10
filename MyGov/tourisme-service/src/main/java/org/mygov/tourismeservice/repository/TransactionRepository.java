package org.mygov.tourismeservice.repository;

import org.mygov.tourismeservice.entities.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TransactionRepository extends JpaRepository<Transactions, Long> {
}
