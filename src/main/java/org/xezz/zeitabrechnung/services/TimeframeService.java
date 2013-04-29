package org.xezz.zeitabrechnung.services;

import org.xezz.zeitabrechnung.model.Coworker;
import org.xezz.zeitabrechnung.model.Project;
import org.xezz.zeitabrechnung.model.Timeframe;

import java.util.Date;
import java.util.List;

/**
 * User: Xezz
 * Date: 29.04.13
 * Time: 14:37
 * Service to receive certain Timeframes by a criteria
 */
public interface TimeframeService {

    /**
     * Finds all Timeframes for the Coworker.
     * @param coworker Coworker
     * @return Timeframes
     */
    List<Timeframe> timeframesForCoworker(Coworker coworker);

    /**
     * Finds all Timeframes for the Project.
     * @param project Project
     * @return Timeframes
     */
    List<Timeframe> timeframesForProject(Project project);

    /**
     * Create a new Timeframe
     * @param c Coworker working on the Project
     * @param p Project for this Timeframe
     * @param startTime Date the starting time of the timeframe
     * @param endTime Date the ending time of the timeframe
     */
    void addNewTimeframe(Coworker c, Project p, Date startTime, Date endTime);

    /**
     * Get a specific timeframe by its ID
     * @param id Long identifier
     * @return Timeframe the looked for timeframe, return {code null} when there is no Entity with this id
     */
    Timeframe getTimeFrameById(Long id);

}
