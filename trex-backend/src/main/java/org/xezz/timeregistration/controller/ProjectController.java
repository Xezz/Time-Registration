package org.xezz.timeregistration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.xezz.timeregistration.dao.ProjectDAO;
import org.xezz.timeregistration.service.ProjectService;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * User: Xezz
 * Date: 05.05.13
 * Time: 11:18
 * Handle requests for Projects
 */
@Controller
@RequestMapping(value = "api/project")
public class ProjectController {

    private Validator validator;
    ProjectService service;

    @Autowired
    public void setService(ProjectService service) {
        this.service = service;
    }

    @Resource(name = "projectValidator")
    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    /**
     * Enable binding with a Spring Validator
     *
     * @param binder
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    /**
     * Request all Projects
     *
     * @return List of all Projects
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Iterable<ProjectDAO> getAll() {
        return service.getAll();
    }

    /**
     * Request to create a new Project
     *
     * @param projectDAO The Project to persist
     * @return Persisted Project
     */
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ProjectDAO create(@Valid @RequestBody ProjectDAO projectDAO) {
        return service.addNew(projectDAO);
    }

    /**
     * Request to update an existing Project
     *
     * @param projectDAO The Project to update
     * @return Updated Project
     */
    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ProjectDAO update(@Valid @RequestBody ProjectDAO projectDAO) {
        return service.update(projectDAO);
    }

    /**
     * Request to get a Project by its ID
     *
     * @param id Long the ID of the Project
     * @return requested Project
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ProjectDAO getById(@PathVariable("id") Long id) {
        return service.getById(id);
    }

    /**
     * Request to get all Projects with a given name
     *
     * @param name String the name of the Project
     * @return All matching Projects
     */
    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Iterable<ProjectDAO> getByName(@PathVariable("name") String name) {
        return service.getByName(name);
    }

    /**
     * Delete a given project
     *
     * @param projectDAO the Project to delete
     */
    @RequestMapping(method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void delete(@Valid @RequestBody ProjectDAO projectDAO) {
        service.delete(projectDAO);
    }
}
