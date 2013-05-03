package org.xezz.timeregistration.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xezz.timeregistration.model.Coworker;
import org.xezz.timeregistration.model.Customer;
import org.xezz.timeregistration.model.Project;
import org.xezz.timeregistration.model.Timeframe;
import org.xezz.timeregistration.repositories.CustomerRepository;
import org.xezz.timeregistration.services.CustomerService;

import java.util.List;

/**
 * User: Xezz
 * Date: 29.04.13
 * Time: 22:44
 */
//TODO: Is it right to use @Service?
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository repo;

    @Override
    public List<Customer> customerByName(String name) {
        return repo.findByName(name);
    }

    @Override
    public List<Customer> customerByNameMatch(String name) {
        // TODO: Revisit this
        // make sure we actually like
        // Should most likely not do it for now :D
        StringBuilder builder = new StringBuilder();
        if (!name.startsWith("%")) {
            builder.append("%");
        }
        builder.append(name);
        if (!name.endsWith("%")) {
            builder.append("%");
        }
        return repo.findByNameLike(builder.toString());
    }

    @Override
    public Customer customerByProject(Project p) {
        return p.getCustomer();
    }

    @Override
    public Customer customerByTimeFrame(Timeframe t) {
        return t.getProject().getCustomer();
    }

    @Override
    public List<Customer> customerByCoworker(Coworker c) {
        return null;  // FIXME: Implement this method
    }
}
