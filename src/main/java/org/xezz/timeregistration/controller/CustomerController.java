package org.xezz.timeregistration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.xezz.timeregistration.model.Customer;
import org.xezz.timeregistration.services.CustomerService;

import java.util.List;

/**
 * User: Xezz
 * Date: 05.05.13
 * Time: 11:17
 */
@Controller
@RequestMapping(value = "customer")
public class CustomerController {

    @Autowired
    CustomerService service;

    /**
     * Request all Customers
     * @return List of all Customers
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Customer> getAll() {
        return service.customersAll();
    }

    /**
     * Save a new Customer
     * @param customer Customer to save
     * @return Customer that got saved
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Customer saveCustomer(@RequestBody Customer customer) {
        // TODO: Verify and Nullcheck
        return service.addNewCustomer(customer);
    }

    /**
     * Update an existing Customer
     * @param customer Customer with new values
     * @return Customer that got updated
     */
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public Customer updateCustomer(@RequestBody Customer customer){
        // TODO: Verify and Nullcheck
        return service.updateCustomer(customer);
    }

    /**
     * Get a Customer by its id
     * @param id Long the id of the Customer
     * @return Customer that matches this id
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @ResponseBody
    public Customer getCustomerById(@PathVariable("id") Long id) {
        return service.customerById(id);
    }

    /**
     * Get all Customers by part of their name
     * @param name String name to match
     * @return List of all Customers matching the given name
     */
    @RequestMapping(method = RequestMethod.GET, value = "/name/{name}")
    @ResponseBody
    public List<Customer> getCustomerByNameMatch(@PathVariable("name") String name) {
        return service.customerByNameMatch(name);
    }
}