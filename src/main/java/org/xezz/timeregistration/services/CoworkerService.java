package org.xezz.timeregistration.services;

import org.xezz.timeregistration.dao.CoworkerDAO;
import org.xezz.timeregistration.dao.ProjectDAO;
import org.xezz.timeregistration.dao.TimeSpanDAO;

/**
 * Service to receive Coworkers by a criteria
 * User: Xezz
 * Date: 29.04.13
 * Time: 14:36
 */
public interface CoworkerService {

    /**
     * Get all coworkers
     *
     * @return List of all Coworkers
     */
    Iterable<CoworkerDAO> coworkersAll();

    /**
     * Get all coworkers with this first name
     *
     * @param firstName String the first name of the Coworkers
     * @return List of all Coworkers with this first name
     */
    Iterable<CoworkerDAO> coworkersByFirstName(String firstName);

    /**
     * Get all Coworkers with this last name
     *
     * @param lastName String the last name of the Coworkers
     * @return List of all Coworkers with this last name
     */
    Iterable<CoworkerDAO> coworkersByLastName(String lastName);

    /**
     * Get all Coworkers that match the given first and last name
     *
     * @param firstName String the first name of the coworker
     * @param lastName  String the last name of the coworker
     * @return List of all Coworkers that match the first and last name
     */
    Iterable<CoworkerDAO> coworkersByFirstAndLastName(String firstName, String lastName);

    /**
     * Get a specific Coworker by its ID
     *
     * @param id Long the unique ID of a Coworker
     * @return Coworker that has this id or if none found {@code null}
     */
    CoworkerDAO coworkerById(Long id);

    // FIXME: Is this really needed? Coworker is stored already in the timeframe

    /**
     * Get a Coworker by a specific timeSpan
     *
     * @param timeSpanDAO TimeSpan a Coworker worked on
     * @return Coworker that is stored in the TimeSpan
     */
    CoworkerDAO coworkerByTimeFrame(TimeSpanDAO timeSpanDAO);

    /**
     * Get all Coworkers that worked for a specific Project
     *
     * @param p Project to get all Coworkers
     * @return List of all Coworkers involved in the Project
     */
    Iterable<CoworkerDAO> coworkersByProject(ProjectDAO p);

    /**
     * Persist a new Coworker
     *
     * @param coworkerDAO the Coworker to persist
     * @return Coworker the persisted Coworker
     */
    CoworkerDAO addNewCoworker(CoworkerDAO coworkerDAO);

    /**
     * Update an existing Coworker
     *
     * @param coworkerDAO the new Coworker
     * @return Coworker the persisted Coworker
     */
    CoworkerDAO updateCoworker(CoworkerDAO coworkerDAO);
}
