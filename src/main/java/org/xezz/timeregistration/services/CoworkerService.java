package org.xezz.timeregistration.services;

import org.xezz.timeregistration.model.Coworker;
import org.xezz.timeregistration.model.Project;
import org.xezz.timeregistration.model.TimeSpan;

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
    Iterable<Coworker> coworkersAll();

    /**
     * Get all coworkers with this first name
     *
     * @param firstName String the first name of the Coworkers
     * @return List of all Coworkers with this first name
     */
    Iterable<Coworker> coworkersByFirstName(String firstName);

    /**
     * Get all Coworkers with this last name
     *
     * @param lastName String the last name of the Coworkers
     * @return List of all Coworkers with this last name
     */
    Iterable<Coworker> coworkersByLastName(String lastName);

    /**
     * Get all Coworkers that match the given first and last name
     *
     * @param firstName String the first name of the coworker
     * @param lastName  String the last name of the coworker
     * @return List of all Coworkers that match the first and last name
     */
    Iterable<Coworker> coworkersByFirstAndLastName(String firstName, String lastName);

    /**
     * Get a specific Coworker by its ID
     *
     * @param id Long the unique ID of a Coworker
     * @return Coworker that has this id or if none found {@code null}
     */
    Coworker coworkerById(Long id);

    // FIXME: Is this really needed? Coworker is stored already in the timeframe

    /**
     * Get a Coworker by a specific timeSpan
     *
     * @param timeSpan TimeSpan a Coworker worked on
     * @return Coworker that is stored in the TimeSpan
     */
    Coworker coworkerByTimeFrame(TimeSpan timeSpan);

    /**
     * Get all Coworkers that worked for a specific Project
     *
     * @param p Project to get all Coworkers
     * @return List of all Coworkers involved in the Project
     */
    Iterable<Coworker> coworkersByProject(Project p);

    /**
     * Persist a new Coworker
     *
     * @param coworker the Coworker to persist
     * @return Coworker the persisted Coworker
     */
    Coworker addNewCoworker(Coworker coworker);

    /**
     * Update an existing Coworker
     *
     * @param coworker the new Coworker
     * @return Coworker the persisted Coworker
     */
    Coworker updateCoworker(Coworker coworker);
}
