package org.xezz.timeregistration.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.xezz.timeregistration.model.Coworker;
import org.xezz.timeregistration.model.Project;

/**
 * User: Xezz
 * Date: 26.04.13
 * Time: 16:56
 * see also http://static.springsource.org/spring-data/data-jpa/snapshot-site/reference/html/
 */
@Repository
public interface CoworkerRepository extends CrudRepository<Coworker, Long> {
    /**
     * Find all Coworkers with this first name
     *
     * @param firstName String the first name of the Coworker
     * @return List of all Coworkers that match the given first name
     */
    Iterable<Coworker> findByFirstName(String firstName);

    /**
     * Find all Coworkers with this last name
     *
     * @param lastName String the last name of the Coworker
     * @return List of all Coworkers that match the given last name
     */
    Iterable<Coworker> findByLastName(String lastName);

    /**
     * Find all Coworker with the given first and last name
     *
     * @param firstName String the first name
     * @param lastName  String the last name
     * @return List of all Coworkers matching the last and first name
     */
    Iterable<Coworker> findByFirstNameAndLastName(String firstName, String lastName);

    // Get the Coworker from a timeframe, when the timeframe contains a project

    /**
     * Get all Coworkers that worked on the Project
     *
     * @param project Project that you need Coworkers from
     * @return List of all Coworkers that have worked on the give project
     */
    @Query("SELECT t.coworker FROM TimeSpan t WHERE t.project = :project")
    Iterable<Coworker> findCoworkersByProject(@Param("project") Project project);

    /**
     * Get all Coworkers
     *
     * @return List of all Coworkers
     */
    @Query("SELECT c FROM Coworker c")
    Iterable<Coworker> findAllCoworkers();
}
