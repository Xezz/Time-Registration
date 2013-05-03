package org.xezz.timeregistration.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.xezz.timeregistration.model.Coworker;
import org.xezz.timeregistration.model.Project;

import java.util.List;

/**
 * User: Xezz
 * Date: 27.04.13
 * Time: 22:07
 */
public interface ProjectRepository extends CrudRepository<Project, Long> {

    /**
     * Find all projects that match the exact name
     * @param name String to match exactly for the name
     * @return List of all matching Projects
     */
    public List<Project> findByName(String name);

    /**
     * Find all projects that contain the given name, use % inside the parameter for matching
     * @param name String to match, use % for matching
     * @return List of all Projects that match the given name
     */
    public List<Project> findByNameLike(String name);

    // Get the Projects from a timeframe, when the timeframe contains a coworker
    // TODO: Verify this query works (JUnit)
    /**
     * Query the repository for all Projects that the given Coworker worked on
     * @param coworker Coworker involved into the Project
     * @return List of all Projects the Coworker worked on
     */
    @Query("SELECT t.project FROM Timeframe t WHERE t.coworker = :coworker")
    public List<Project> findProjectsByCoworker(@Param("project")Coworker coworker);
}
