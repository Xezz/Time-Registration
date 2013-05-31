package org.xezz.timeregistration.services;

import org.xezz.timeregistration.dao.CoworkerDAO;
import org.xezz.timeregistration.dao.ProjectDAO;
import org.xezz.timeregistration.dao.TimeSpanDAO;

/**
 * User: Xezz
 * Date: 29.04.13
 * Time: 14:37
 * Service to receive certain Timeframes by a criteria
 */
public interface TimeSpanService {

    /**
     * Find all TimeSpans for the Coworker.
     *
     * @param coworkerDAO Coworker
     * @return TimeSpans
     */
    Iterable<TimeSpanDAO> timeSpansForCoworker(CoworkerDAO coworkerDAO);

    /**
     * Find all TimeSpans for the Project.
     *
     * @param projectDAO Project
     * @return TimeSpans
     */
    Iterable<TimeSpanDAO> timeSpansForProject(ProjectDAO projectDAO);

    /**
     * Get a specific time span by its ID
     *
     * @param id Long identifier
     * @return TimeSpan the looked for time span, return {@code null} when there is no Entity with this id
     */
    TimeSpanDAO getTimeSpanById(Long id);

    /**
     * Get all TimeSpans
     *
     * @return Iterable of all found TimeSpans
     */
    Iterable<TimeSpanDAO> findAllTimeSpans();

    /**
     * Create a new TimeSpan
     *
     * @param timeSpanDAO TimeSpan to create
     * @return the persisted TimeSpan
     */
    TimeSpanDAO createNewTimeSpan(TimeSpanDAO timeSpanDAO);

    /**
     * Update an existing TimeSpan
     *
     * @param timeSpanDAO TimeSpan to update
     * @return the updated TimeSpan
     */
    TimeSpanDAO updateTimeSpan(TimeSpanDAO timeSpanDAO);


}
