package org.xezz.timeregistration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.xezz.timeregistration.dao.CustomerDAO;
import org.xezz.timeregistration.service.CustomerService;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * User: Xezz
 * Date: 05.05.13
 * Time: 11:17
 */
@Controller
@RequestMapping(value = "api/customer")
public class CustomerController {

    private Validator validator;
    CustomerService service;

    @Autowired
    public void setService(CustomerService service) {
        this.service = service;
    }

    @Resource(name = "customerValidator")
    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    /**
     * Enable binding with a Spring Validator
     *
     * @param binder
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    /**
     * Request all Customers
     *
     * @return List of all Customers
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Iterable<CustomerDAO> getAll() {
        return service.getAll();
    }

    /**
     * Save a new Customer
     *
     * @param customerDAO Customer to save
     * @return Customer that got saved
     */
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CustomerDAO saveCustomer(@Valid @RequestBody CustomerDAO customerDAO) {
        return service.addNew(customerDAO);
    }

    /**
     * Update an existing Customer
     *
     * @param customerDAO Customer with new values
     * @return Customer that got updated
     */
    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CustomerDAO updateCustomer(@Valid @RequestBody CustomerDAO customerDAO) {
        return service.update(customerDAO);
    }

    /**
     * Delete an existing Customer
     *
     * @param customerDAO Customer to delete
     */
    @RequestMapping(method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteCustomer(@Valid @RequestBody CustomerDAO customerDAO) {
        service.delete(customerDAO);
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
        return service.getById(id);
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
        return service.getByNameMatch(name);
    }
}
