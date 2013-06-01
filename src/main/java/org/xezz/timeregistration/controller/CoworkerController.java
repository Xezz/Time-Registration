package org.xezz.timeregistration.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.xezz.timeregistration.dao.CoworkerDAO;
import org.xezz.timeregistration.services.CoworkerService;


/**
 * Handle the requests for Coworkers
 * User: Xezz
 * Date: 03.05.13
 * Time: 14:05
 */
// Make this a controller
@Controller
// requests are mapped to /coworker
@RequestMapping(value = "api/coworker")
public class CoworkerController {
    // DI the service
    @Autowired
    CoworkerService service;
    private final static Logger LOGGER = LoggerFactory.getLogger(CoworkerController.class);

    // Starting with JSON mapping
    //
    // RESTful mapping

    /**
     * Get all Coworkers via REST request (method GET)
     *
     * @return List of all Coworkers
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Iterable<CoworkerDAO> getAll() {
        LOGGER.info("Request to get all Coworker");
        return service.coworkersAll();
    }

    /**
     * Get a Coworker by its id
     *
     * @param id Long the ID of the coworker
     * @return Coworker with that id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CoworkerDAO get(@PathVariable("id") Long id) {
        LOGGER.info("Request to get Coworker by id: " + id);
        final CoworkerDAO coworkerDAO = service.coworkerById(id);
        LOGGER.info("Coworker found == null? " + (coworkerDAO == null));
        if (coworkerDAO != null) {
            LOGGER.info("Coworker: " + coworkerDAO.getFirstName() + " " + coworkerDAO.getLastName());
        }
        return coworkerDAO;
    }

    /**
     * Get a List of all Coworkers with the given first name
     *
     * @param firstname String the first name of the Coworker
     * @return List of all Coworkers with that first name
     */
    @RequestMapping(value = "/firstname/{firstname}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Iterable<CoworkerDAO> getByFirstName(@PathVariable("firstname") String firstname) {
        LOGGER.info("Request to get Coworker by firstname: " + ((firstname != null) ? firstname : "null"));
        return service.coworkersByFirstName(firstname);
    }

    /**
     * Get all Coworkers with the given last name
     *
     * @param lastname String the last name of th Coworker
     * @return List of all Coworkers with that last name
     */
    @RequestMapping(value = "/lastname/{lastname}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Iterable<CoworkerDAO> getByLastName(@PathVariable("lastname") String lastname) {
        LOGGER.info("Request to get Coworker by lastname: " + ((lastname != null) ? lastname : "null"));
        return service.coworkersByLastName(lastname);
    }

    /**
     * Create a new Coworker
     *
     * @param coworkerDAO the mapped Coworker
     * @return id of the newly created Coworker
     */
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CoworkerDAO create(@RequestBody CoworkerDAO coworkerDAO) {
        LOGGER.info("Post to create Coworker.");
        if (coworkerDAO == null) {
            LOGGER.info("Coworker == null");
        } else {
            LOGGER.info("Coworker.firstName: " + coworkerDAO.getFirstName() + " Coworker.lastName: " + coworkerDAO.getLastName());
        }
        return service.addNewCoworker(coworkerDAO);
    }

    /**
     * Update an existing Coworker
     *
     * @param coworkerDAO updated Coworker
     * @return Coworker persisted Coworker
     */
    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CoworkerDAO update(@RequestBody CoworkerDAO coworkerDAO) {
        LOGGER.info("JSON PUT Request to update coworker");
        return service.updateCoworker(coworkerDAO);
    }

    /**
     * DELETE an existing Coworker
     *
     * @param coworkerDAO updated Coworker
     * @return Coworker persisted Coworker
     */
    // FIXME: RETURN Correct HTTP HEADER INSTEAD
    @RequestMapping(method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@RequestBody CoworkerDAO coworkerDAO) {
        LOGGER.info("JSON DELETE Request to delete a coworker");
        service.deleteCoworker(coworkerDAO);
    }

    // MVC mapping to jsp
    //
    // View-based mapping

    /**
     * Receive a list of all Coworkers to be displayed via a jsp View
     *
     * @param model the model to be passed to the View
     * @return String describing where to find the corresponding view
     */
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        LOGGER.info("REQUEST for NON JSON .... list");
        model.addAttribute("coworkers", getAll());

        // map to WEB-INF/jsp/coworkers/list.jsp
        return "coworkers/list";
    }

    /**
     * Receive a Coworker by its ID to be displayed via a jsp View
     *
     * @param id    Long - Distinct identifier
     * @param model the model to be passed to the View
     * @return String describing where to find the corresponding view
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String showById(@PathVariable("id") Long id, Model model) {
        LOGGER.info("REQUEST NON JSON ... get by id");
        model.addAttribute(get(id));

        // map to WEB-INF/jsp/coworkers/show.jsp
        return "coworkers/show";
    }

    /**
     * Receive all Coworker with the given first name to be be displayed via a jsp View
     *
     * @param firstName String - the first name to look for
     * @param model     the model to be passed to the View
     * @return String describing where to find the corresponding view
     */
    @RequestMapping(value = "/firstname/{firstname}", method = RequestMethod.GET)
    public String showByFirstName(@PathVariable("firstname") String firstName, Model model) {
        LOGGER.info("REQUEST NON JSON ... by firstname");
        model.addAttribute("coworkers", getByFirstName(firstName));

        // map to WEB-INF/jsp/coworkers/list.jsp
        return "coworkers/list";
    }

    /**
     * Receive all Coworker with the given last name to be displayed via a jsp View
     *
     * @param lastName String - The last name to look for
     * @param model    the model to be passed to the View
     * @return String describing where to find the corresponding view
     */
    // TODO: Remove this as soon as RESTful is stable
    @RequestMapping(value = "/lastname/{lastname}", method = RequestMethod.GET)
    public String showByLastName(@PathVariable("lastname") String lastName, Model model) {
        LOGGER.info("REQUEST NON JSON ... by lastname");
        model.addAttribute("coworkers", getByLastName(lastName));

        // map to WEB-INF/jsp/coworkers/list.jsp
        return "coworkers/list";
    }
}
