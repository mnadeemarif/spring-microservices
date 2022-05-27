package org.nadeemarif.customer.services;

import org.nadeemarif.customer.models.Customer;
import org.nadeemarif.customer.models.FraudCheckResponse;
import org.nadeemarif.customer.models.requests.CustomerRegistrationRequest;
import org.nadeemarif.customer.repositories.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public record CustomerService(CustomerRepository customerRepository, RestTemplate restTemplate) {
    public void register(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        // todo check valid email
        // todo check email not taken
        customerRepository.saveAndFlush(customer);
        // todo check if fraudster
        FraudCheckResponse response = restTemplate.getForObject(
                "http://FRAUD/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId()
        );
        if(response.isFraudulent()){
            throw new IllegalStateException("Customer is fraudster");
        }
        //todo send notification
    }
}
