package org.xezz.zeitabrechnung.repositories;

import org.springframework.data.repository.CrudRepository;
import org.xezz.zeitabrechnung.model.Coworker;

/**
 * User: Xezz
 * Date: 26.04.13
 * Time: 16:56
 * see also http://static.springsource.org/spring-data/data-jpa/snapshot-site/reference/html/
 */
public interface CoworkerRepository extends CrudRepository<Coworker, Long> {
}
