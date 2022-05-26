package org.nadeemarif.fraud.repositories;

import org.nadeemarif.fraud.models.FraudCheckHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FraudRepository extends JpaRepository<FraudCheckHistory, Integer> {
}
