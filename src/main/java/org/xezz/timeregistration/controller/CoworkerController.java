package org.xezz.timeregistration.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.xezz.timeregistration.model.Coworker;
import org.xezz.timeregistration.services.CoworkerService;

import java.util.List;

/**
 * Handle the requests for Coworkers
 * User: Xezz
 * Date: 03.05.13
 * Time: 14:05
 */
// Make this a controller
@Controller
// requests are mapped to /coworker
@RequestMapping(value = "coworker")
public class CoworkerController {
    // DI the service
    @Autowired
    CoworkerService service;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Get all Coworkers via REST request (method GET)
     * @return List of all Coworkers
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Coworker> getAll() {
        logger.info("Request to get all Coworker");
        return service.coworkersAll();
    }

    /**
     * Get a Coworker by its id
     * @param id Long the ID of the coworker
     * @return Coworker with that id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Coworker get(@PathVariable("id") Long id) {
        logger.info("Request to get Coworker by id: " + id);
        final Coworker coworker = service.coworkerById(id);
        logger.info("Coworker found == null? " + (coworker==null));
        if (coworker != null) {
            logger.info("Coworker: " + coworker.getFirstName() + " " + coworker.getLastName());
        }
        return coworker;
    }

    /**
     * Get a List of all Coworkers with the given first name
     * @param firstname String the first name of the Coworker
     * @return List of all Coworkers with that first name
     */
    @RequestMapping(value = "/firstname/{firstname}", method = RequestMethod.GET)
    @ResponseBody
    public List<Coworker> getByFirstName(@PathVariable("firstname") String firstname) {
        logger.info("Request to get Coworker by firstname: " + ((firstname != null) ? firstname : "null"));
        return service.coworkersByFirstName(firstname);
    }

    /**
     * Get all Coworkers with the given last name
     * @param lastname String the last name of th Coworker
     * @return List of all Coworkers with that last name
     */
    @RequestMapping(value = "/lastname/{lastname}", method = RequestMethod.GET)
    @ResponseBody
    public List<Coworker> getByLastName(@PathVariable("lastname") String lastname) {
        logger.info("Request to get Coworker by lastname: " + ((lastname != null) ? lastname : "null"));
        return service.coworkersByLastName(lastname);
    }

    /**
     * Create a new Coworker
     * @param coworker the mapped Coworker
     * @return id of the newly created Coworker
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Long create(@RequestBody Coworker coworker) {
        logger.info("Post to create Coworker.");
        if (coworker == null) {
            logger.info("Coworker == null");
        } else {
            logger.info("Coworker.firstName: " + coworker.getFirstName() + " Coworker.lastName: " + coworker.getLastName());
        }
        final Coworker coworker1 = service.addNewCoworker(coworker);
        logger.info("Persisted coworker, id: " + coworker1.getCoworkerId());
        return coworker1.getCoworkerId();
    }

    /**
     * Update an existing Coworker
     * @param coworker updated Coworker
     * @return String describing the status of the update
     */
    // FIXME: what the heck should one return here
    // TODO: Figure out what model and bindingresult are good for
    @RequestMapping(method = RequestMethod.PUT)
    public String update(@RequestBody Coworker coworker, BindingResult bindingResult, Model model) {
        final Coworker updatedCoworker = service.updateCoworker(coworker);
        return (updatedCoworker != null) ? "redirect:/id/" + coworker.getCoworkerId() : "failed";
    }

}
