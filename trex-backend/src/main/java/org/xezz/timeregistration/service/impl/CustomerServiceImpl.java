package org.xezz.timeregistration.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xezz.timeregistration.dao.CoworkerDAO;
import org.xezz.timeregistration.dao.CustomerDAO;
import org.xezz.timeregistration.dao.ProjectDAO;
import org.xezz.timeregistration.dao.TimeSpanDAO;
import org.xezz.timeregistration.model.Customer;
import org.xezz.timeregistration.repository.CustomerRepository;
import org.xezz.timeregistration.repository.ProjectRepository;
import org.xezz.timeregistration.repository.TimeSpanRepository;
import org.xezz.timeregistration.service.CustomerService;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Xezz
 * Date: 29.04.13
 * Time: 22:44
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    TimeSpanRepository timeSpanRepository;

    @Override
    public Iterable<CustomerDAO> customerByName(String name) {
        final Iterable<Customer> byName = customerRepository.findByName(name);
        final List<CustomerDAO> cList = new ArrayList<CustomerDAO>();
        for (Customer c : byName) {
            cList.add(new CustomerDAO(c));
        }
        return cList;
    }

    @Override
    public Iterable<CustomerDAO> customerByNameMatch(String name) {
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
        final Iterable<Customer> byNameLike = customerRepository.findByNameLike(builder.toString());
        final List<CustomerDAO> cList = new ArrayList<CustomerDAO>();
        for (Customer c : byNameLike) {
            cList.add(new CustomerDAO(c));
        }
        return cList;
    }

    // FIXME: NULL CHECK too lazy right now
    @Override
    public CustomerDAO customerByProject(ProjectDAO p) {
        return customerById(projectRepository.findOne(p.getProjectId()).getCustomer().getCustomerId());
    }

    // FIXME: LOTSA NULL CHECKS HERE NEEDED
    @Override
    public CustomerDAO customerByTimeFrame(TimeSpanDAO t) {
        return customerById(timeSpanRepository.findOne(t.getTimeSpanId()).getCoworker().getCoworkerId());
    }

    @Override
    public Iterable<CustomerDAO> customerByCoworker(CoworkerDAO c) {
        final Iterable<Customer> customersByCoworker = customerRepository.findCustomersByCoworker(c);
        final List<CustomerDAO> daoList = new ArrayList<CustomerDAO>();
        for (Customer customer : customersByCoworker) {
            daoList.add(new CustomerDAO(customer));
        }
        return daoList;
    }

    @Override
    public Iterable<CustomerDAO> customersAll() {
        final Iterable<Customer> allCustomers = customerRepository.findAllCustomers();
        final List<CustomerDAO> customerDAOList = new ArrayList<CustomerDAO>();
        for (Customer c : allCustomers) {
            customerDAOList.add(new CustomerDAO(c));
        }
        return customerDAOList;
    }

    @Override
    public CustomerDAO customerById(Long id) {
        return new CustomerDAO(customerRepository.findOne(id));
    }

    @Transactional
    @Override
    public CustomerDAO addNewCustomer(CustomerDAO c) {
        return new CustomerDAO(customerRepository.save(new Customer(c)));
    }

    @Transactional
    @Override
    public CustomerDAO updateCustomer(CustomerDAO c) {
        if (customerRepository.exists(c.getCustomerId())) {
            return addNewCustomer(c);
        }
        // TODO: Can also save the Customer anyway, instead of discarding
        // This would be against pure REST, since a PUT means a resource exists already
        // On the other hand, might just do it sloppy
        return null;
    }

    @Override
    public void deleteCustomer(CustomerDAO customerDAO) {
        final Customer customer = customerRepository.findOne(customerDAO.getCustomerId());
        if (customer != null) {
            customerRepository.delete(customer);
        }
    }
}
