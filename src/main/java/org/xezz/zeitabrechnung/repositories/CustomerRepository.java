package org.xezz.zeitabrechnung.repositories;

import org.springframework.data.repository.CrudRepository;
import org.xezz.zeitabrechnung.model.Customer;

/**
 * User: Xezz
 * Date: 27.04.13
 * Time: 22:06
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
