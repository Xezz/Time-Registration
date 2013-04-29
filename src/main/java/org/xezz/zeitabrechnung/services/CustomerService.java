package org.xezz.zeitabrechnung.services;

import org.xezz.zeitabrechnung.model.Coworker;
import org.xezz.zeitabrechnung.model.Customer;
import org.xezz.zeitabrechnung.model.Project;
import org.xezz.zeitabrechnung.model.Timeframe;

import java.util.List;

/**
 * User: Xezz
 * Date: 29.04.13
 * Time: 14:36
 */
public interface CustomerService {
    /**
     * Receive Customers by their name
     * @param name String the name of the Customer
     * @return List of Customers that matches a give name
     */
    List<Customer> customerByName(String name);

    /**
     * Receive all Customers where a part of its name matches the given name
     * @param name String part of the name to match
     * @return List of Customers that match a name partially
     */
    List<Customer> customerByNameMatch(String name);

    /**
     * Get the Customer of a Project
     * @param p Project to get a Customer of
     * @return Customer that owns the Project
     */
    Customer customerByProject(Project p);

    /**
     * Get a Customer that is associated to a timeframe
     * @param t Timeframe of concern
     * @return Customer that gets charged for the timeframe
     */
    Customer customerByTimeFrame(Timeframe t);

    /**
     * Get all Customers the given Coworker worked for
     * @param c Coworker in concern
     * @return List of Customers the given Coworker was involved with
     */
    List<Customer> customerByCoworker(Coworker c);
}
