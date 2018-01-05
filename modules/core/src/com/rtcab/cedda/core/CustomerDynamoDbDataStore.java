package com.rtcab.cedda.core;

import com.haulmont.cuba.core.app.DataStore;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.entity.KeyValueEntity;
import com.haulmont.cuba.core.global.CommitContext;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.core.global.ValueLoadContext;
import com.rtcab.cedda.core.repository.CustomerRepository;
import com.rtcab.cedda.entity.Customer;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import javax.inject.Inject;
import java.util.*;
import java.util.stream.Collectors;

@Component("cedda_customerDynamoDbDataStore")
public class CustomerDynamoDbDataStore implements DataStore {

    @Inject
    CustomerRepository customerRepository;

    @Nullable
    @Override
    public <E extends Entity> E load(LoadContext<E> context) {

        UUID customerId = (UUID) context.getId();

        return (E) customerRepository.findOne(customerId);
    }

    @Override
    public <E extends Entity> List<E> loadList(LoadContext<E> context) {
        List<E> allCustomers = (List<E>) customerRepository.findAll();
        List<E> list = allCustomers.stream().collect(Collectors.toList());
        return list;
    }

    @Override
    public long getCount(LoadContext<? extends Entity> context) {
        return customerRepository.count();
    }

    @Override
    public Set<Entity> commit(CommitContext context) {
        Collection<Entity> commitInstances = context.getCommitInstances();


        Collection<Customer> customers = new LinkedList<>();
        for (Entity commitInstance : commitInstances) {
            customers.add((Customer) commitInstance);
        }

        customerRepository.save(customers);
        return new HashSet<>();
    }

    @Override
    public List<KeyValueEntity> loadValues(ValueLoadContext context) {
        return null;
    }
}
