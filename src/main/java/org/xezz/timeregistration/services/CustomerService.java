package org.xezz.timeregistration.services;

import org.xezz.timeregistration.model.Coworker;
import org.xezz.timeregistration.model.Customer;
import org.xezz.timeregistration.model.Project;
import org.xezz.timeregistration.model.TimeSpan;

/**
 * User: Xezz
 * Date: 29.04.13
 * Time: 14:36
 */
public interface CustomerService {

    /**
     * Receive all Customers
     *
     * @return List of all Customers
     */

    Iterable<Customer> customersAll();

    /**
     * Get a Customer by its id
     *
     * @param id Long the id of a given Customer
     * @return Customer that has the id
     */
    Customer customerById(Long id);

    /**
     * Persist a new Customer
     *
     * @param c Customer to persist
     * @return Persisted Customer
     */
    Customer addNewCustomer(Customer c);

    /**
     * Update an existing Customer
     *
     * @param c Customer that has been updated
     * @return Customer after persisting
     */
    Customer updateCustomer(Customer c);

    /**
     * Receive Customers by their name
     *
     * @param name String the name of the Customer
     * @return List of Customers that matches a give name
     */
    Iterable<Customer> customerByName(String name);

    /**
     * Receive all Customers where a part of its name matches the given name
     *
     * @param name String part of the name to match
     * @return List of Customers that match a name partially
     */
    Iterable<Customer> customerByNameMatch(String name);

    /**
     * Get the Customer of a Project
     *
     * @param p Project to get a Customer of
     * @return Customer that owns the Project
     */
    Customer customerByProject(Project p);

    /**
     * Get a Customer that is associated to a timeframe
     *
     * @param t TimeSpan of concern
     * @return Customer that gets charged for the timeframe
     */
    Customer customerByTimeFrame(TimeSpan t);

    /**
     * Get all Customers the given Coworker worked for
     *
     * @param c Coworker in concern
     * @return List of Customers the given Coworker was involved with
     */
    Iterable<Customer> customerByCoworker(Coworker c);
}
