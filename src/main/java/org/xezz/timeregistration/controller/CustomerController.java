package org.xezz.timeregistration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.xezz.timeregistration.dao.CustomerDAO;
import org.xezz.timeregistration.services.CustomerService;

/**
 * User: Xezz
 * Date: 05.05.13
 * Time: 11:17
 */
@Controller
@RequestMapping(value = "api/customer")
public class CustomerController {

    @Autowired
    CustomerService service;

    /**
     * Request all Customers
     *
     * @return List of all Customers
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Iterable<CustomerDAO> getAll() {
        return service.customersAll();
    }

    /**
     * Save a new Customer
     *
     * @param customerDAO Customer to save
     * @return Customer that got saved
     */
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CustomerDAO saveCustomer(@RequestBody CustomerDAO customerDAO) {
        // TODO: Verify and Nullcheck
        return service.addNewCustomer(customerDAO);
    }

    /**
     * Update an existing Customer
     *
     * @param customerDAO Customer with new values
     * @return Customer that got updated
     */
    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CustomerDAO updateCustomer(@RequestBody CustomerDAO customerDAO) {
        // TODO: Verify and Nullcheck
        return service.updateCustomer(customerDAO);
    }

    /**
     * Delete an existing Customer
     *
     * @param customerDAO Customer to delete
     */
    @RequestMapping(method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteCustomer(@RequestBody CustomerDAO customerDAO) {
        // TODO: Verify and Nullcheck
        service.deleteCustomer(customerDAO);
    }

    /**
     * Get a Customer by its id
     *
     * @param id Long the id of the Customer
     * @return Customer that matches this id
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CustomerDAO getCustomerById(@PathVariable("id") Long id) {
        return service.customerById(id);
    }

    /**
     * Get all Customers by part of their name
     *
     * @param name String name to match
     * @return List of all Customers matching the given name
     */
    @RequestMapping(method = RequestMethod.GET, value = "/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Iterable<CustomerDAO> getCustomerByNameMatch(@PathVariable("name") String name) {
        return service.customerByNameMatch(name);
    }

    // TODO: Add DELETE for Customers
}
