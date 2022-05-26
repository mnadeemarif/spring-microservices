package org.nadeemarif.fraud.services;

import org.nadeemarif.fraud.models.FraudCheckHistory;
import org.nadeemarif.fraud.repositories.FraudRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public record FraudService(FraudRepository fraudRepository) {

    public boolean isFraudulentCustomer(Integer customerId){
        fraudRepository.save(
                FraudCheckHistory.builder()
                        .customerId(customerId)
                        .isFraudster(false)
                        .createdAt(LocalDateTime.now())
                        .build()
        );
        return false;
    }

}
