package org.xezz.zeitabrechnung.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.xezz.zeitabrechnung.model.Coworker;
import org.xezz.zeitabrechnung.services.CoworkerService;

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

    /**
     * Get all Coworkers via REST request (method GET)
     * @return List of all Coworkers
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Coworker> getAll() {
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
        return service.coworkerById(id);
    }

    /**
     * Get a List of all Coworkers with the given first name
     * @param firstname String the first name of the Coworker
     * @return List of all Coworkers with that first name
     */
    @RequestMapping(value = "/firstname/{firstname}", method = RequestMethod.GET)
    @ResponseBody
    public List<Coworker> getByFirstName(@PathVariable("firstname") String firstname) {
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
        return service.coworkersByLastName(lastname);
    }

    /**
     * Create a new Coworker
     * @param coworker the mapped Coworker
     * @return id of the newly created Coworker
     */
    @RequestMapping(method = RequestMethod.POST)
    public Long create(@RequestBody Coworker coworker) {
        return service.addNewCoworker(coworker).getCoworkerId();
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
