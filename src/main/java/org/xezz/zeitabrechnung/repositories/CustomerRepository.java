package org.xezz.zeitabrechnung.repositories;

import org.springframework.data.repository.CrudRepository;
import org.xezz.zeitabrechnung.model.Customer;

import java.util.List;

/**
 * User: Xezz
 * Date: 27.04.13
 * Time: 22:06
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    /**
     * Fetch all Customers with this name
     * @param name the exact name of the Customer to find
     * @return List of all Customers that have this name
     */
    public List<Customer> findByName(String name);

    /**
     * Fetch all Customers that have a name like this
     * @param name part of the Customers name to match
     * @return List of all Customers that contain this part of its name
     */
    public List<Customer> findByNameLike(String name);

}
