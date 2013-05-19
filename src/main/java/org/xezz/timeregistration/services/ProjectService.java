package org.xezz.timeregistration.services;

import org.xezz.timeregistration.model.Customer;
import org.xezz.timeregistration.model.Project;
import org.xezz.timeregistration.model.TimeSpan;

/**
 * User: Xezz
 * Date: 29.04.13
 * Time: 14:37
 */
public interface ProjectService {
    /**
     * Get all Projects
     *
     * @return List of all Projects
     */
    Iterable<Project> getAll();

    /**
     * List of Projects by name
     *
     * @param name of the Project
     * @return List of all Projects with that name
     */
    Iterable<Project> getByName(String name);

    /**
     * List of Projects by Customer
     *
     * @param c Customer
     * @return List of all Projects of this Customer
     */
    Iterable<Project> getByCustomer(Customer c);

    /**
     * Project by TimeSpan
     *
     * @param t TimeSpan assigned to a Project
     * @return Project that contains this TimeSpan
     */
    Project getByTimeFrame(TimeSpan t);

    /**
     * Project by identifier
     *
     * @param id Long the id
     * @return Project with this id or null if not exist
     */
    Project getById(Long id);

    /**
     * Persist a Project
     *
     * @param p Project to persist
     * @return persisted Project
     */
    Project addNewProject(Project p);

    /**
     * Updated existing Project
     *
     * @param p Project that has been updated
     * @return Project that has been persisted
     */
    Project updateProject(Project p);

}
