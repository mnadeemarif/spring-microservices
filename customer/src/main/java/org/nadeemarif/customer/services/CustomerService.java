package org.nadeemarif.customer.services;

import org.nadeemarif.customer.models.Customer;
import org.nadeemarif.customer.models.requests.CustomerRegistrationRequest;
import org.nadeemarif.customer.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public record CustomerService(CustomerRepository customerRepository) {
    public void register(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        // todo check valid email
        // todo check email not taken
        customerRepository.save(customer);
    }
}
