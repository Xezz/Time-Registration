package org.xezz.timeregistration.services;

import org.xezz.timeregistration.model.Coworker;
import org.xezz.timeregistration.model.Project;
import org.xezz.timeregistration.model.TimeSpan;

import java.util.Date;

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
     * @param coworker Coworker
     * @return TimeSpans
     */
    Iterable<TimeSpan> timeSpansForCoworker(Coworker coworker);

    /**
     * Find all TimeSpans for the Project.
     *
     * @param project Project
     * @return TimeSpans
     */
    Iterable<TimeSpan> timeSpansForProject(Project project);

    /**
     * Create a new TimeSpan
     *
     * @param c         Coworker working on the Project
     * @param p         Project for this TimeSpan
     * @param startTime Date the starting time of the timeframe
     * @param endTime   Date the ending time of the timeframe
     * @return TimeSpan the persisted TimeSpan
     */
    TimeSpan addNewTimeSpan(Coworker c, Project p, Date startTime, Date endTime);

    /**
     * Get a specific time span by its ID
     *
     * @param id Long identifier
     * @return TimeSpan the looked for time span, return {@code null} when there is no Entity with this id
     */
    TimeSpan getTimeSpanById(Long id);

    /**
     * Get all TimeSpans
     *
     * @return Iterable of all found TimeSpans
     */
    Iterable<TimeSpan> findAllTimeSpans();

    /**
     * Create a new TimeSpan
     *
     * @param timeSpan TimeSpan to create
     * @return the persisted TimeSpan
     */
    TimeSpan createNewTimeSpan(TimeSpan timeSpan);

    /**
     * Update an existing TimeSpan
     *
     * @param timeSpan TimeSpan to update
     * @return the updated TimeSpan
     */
    TimeSpan updateTimeSpan(TimeSpan timeSpan);


}
