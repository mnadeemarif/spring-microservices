package org.nadeemarif.fraud.controllers;

import lombok.extern.slf4j.Slf4j;
import org.nadeemarif.fraud.models.responses.FraudCheckResponse;
import org.nadeemarif.fraud.services.FraudService;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/fraud-check")
public record FraudController (FraudService fraudService){

    @GetMapping(path = "{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable Integer customerId){
        boolean isFraudulentCustomer = fraudService.isFraudulentCustomer(customerId);
        log.info("fraud check request for customer {}", customerId);
        return new FraudCheckResponse(isFraudulentCustomer);
    }
}
