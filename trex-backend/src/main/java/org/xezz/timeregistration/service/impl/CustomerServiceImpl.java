package org.xezz.timeregistration.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xezz.timeregistration.dao.CoworkerDAO;
import org.xezz.timeregistration.dao.CustomerDAO;
import org.xezz.timeregistration.dao.ProjectDAO;
import org.xezz.timeregistration.dao.TimeSpanDAO;
import org.xezz.timeregistration.model.Coworker;
import org.xezz.timeregistration.model.Customer;
import org.xezz.timeregistration.model.Project;
import org.xezz.timeregistration.repository.CustomerRepository;
import org.xezz.timeregistration.repository.ProjectRepository;
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

    @Override
    public Iterable<CustomerDAO> getByName(String name) {
        final Iterable<Customer> byName = customerRepository.findByName(name);
        final List<CustomerDAO> cList = new ArrayList<CustomerDAO>();
        for (Customer c : byName) {
            cList.add(new CustomerDAO(c));
        }
        return cList;
    }

    @Override
    public Iterable<CustomerDAO> getByNameMatch(String name) {
        final List<CustomerDAO> cList = new ArrayList<CustomerDAO>();
        if (name == null) {
            // fail gracefully
            return cList;
        }
        // TODO: Revisit this
        StringBuilder builder = new StringBuilder();
        if (!name.startsWith("%")) {
            builder.append("%");
        }
        builder.append(name);
        if (!name.endsWith("%")) {
            builder.append("%");
        }
        final Iterable<Customer> byNameLike = customerRepository.findByNameLike(builder.toString());
        for (Customer c : byNameLike) {
            cList.add(new CustomerDAO(c));
        }
        return cList;
    }

    @Override
    public CustomerDAO getByProject(ProjectDAO p) {
        if (p == null) {
            return null;
        }
        final Project project = projectRepository.findOne(p.getProjectId());
        return project != null && project.getCustomer() != null ? new CustomerDAO(project.getCustomer()) : null;
    }

    @Override
    public CustomerDAO getByTimeSpan(TimeSpanDAO t) {
        if (t == null) {
            return null;
        }
        final Long projectId = t.getProjectId();
        final Project project = projectRepository.findOne(projectId);
        // only return a new DAO if project and the customer are not null (2nd should not happen, but right now better safe than sorry)
        return project != null && project.getCustomer() != null ? new CustomerDAO(project.getCustomer()) : null;
    }

    @Override
    public Iterable<CustomerDAO> getByCoworker(CoworkerDAO c) {
        final List<CustomerDAO> daoList = new ArrayList<CustomerDAO>();
        if (c == null) {
            // fail gracefully
            return daoList;
        }
        final Iterable<Customer> customersByCoworker = customerRepository.findCustomersByCoworker(new Coworker(c));
        for (Customer customer : customersByCoworker) {
            daoList.add(new CustomerDAO(customer));
        }
        return daoList;
    }

    @Override
    public Iterable<CustomerDAO> getAll() {
        final Iterable<Customer> allCustomers = customerRepository.findAllCustomers();
        final List<CustomerDAO> customerDAOList = new ArrayList<CustomerDAO>();
        for (Customer c : allCustomers) {
            customerDAOList.add(new CustomerDAO(c));
        }
        return customerDAOList;
    }

    @Override
    public CustomerDAO getById(Long id) {
        final Customer customer = customerRepository.findOne(id);
        return customer != null ? new CustomerDAO(customer) : null;
    }

    @Transactional
    @Override
    public CustomerDAO addNew(CustomerDAO c) {
        if (c == null) {
            // fail gracefully
            return null;
        }
        return new CustomerDAO(customerRepository.save(new Customer(c)));
    }

    @Transactional
    @Override
    public CustomerDAO update(CustomerDAO c) {
        if (c != null && customerRepository.exists(c.getCustomerId())) {
            return addNew(c);
        }
        // TODO: Can also save the Customer anyway, instead of discarding
        // This would be against pure REST, since a PUT means a resource exists already
        // On the other hand, might just do it sloppy
        return null;
    }

    @Override
    public void delete(CustomerDAO customerDAO) {
        if (customerDAO != null) {
            final Customer customer = customerRepository.findOne(customerDAO.getCustomerId());
            if (customer != null) {
                customerRepository.delete(customer);
            }
        }
    }
}
