package com.rtcab.cedda.core.repository;

import com.rtcab.cedda.entity.Customer;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;


@EnableScan
public interface CustomerRepository extends CrudRepository<Customer, UUID> {
    List<Customer> findByFirstName(String firstName);
}
