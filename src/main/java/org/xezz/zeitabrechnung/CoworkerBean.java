package org.xezz.zeitabrechnung;

import org.xezz.zeitabrechnung.bo.CoworkerBo;
import org.xezz.zeitabrechnung.model.Coworker;

import java.io.Serializable;
import java.util.List;

/**
 * User: Xezz
 * Date: 26.04.13
 * Time: 17:20
 */
public class CoworkerBean implements Serializable {
    // Dependency Injection via Spring
    CoworkerBo coworkerBo;

    /**
     * The given name of the worker, received from the WebUI to create a new coworker
     */
    private String givenName;
    /**
     * The surname of the worker, received from the WebUI to create a new coworker
     */
    private String surName;

    /**
     * Default constructor for DI/JPA/Spring/Whatever
     */
    public CoworkerBean() {}

    /**
     * Get all Coworkers found in the persistence context (aka Database)
     * @return List of all Coworkers
     */
    public List<Coworker> getCoworkerList() {
        return coworkerBo.findAllCoworkers();
    }

    /**
     * Adds a new Coworker to the database via Spring from the given form fields
     */
    public void addCoworker() {
        Coworker cw = new Coworker();
        cw.setGivenName(getGivenName());
        cw.setSurName(getSurName());

        coworkerBo.addCoworker(cw);

        clearForm();

        // Return, because if I change this to String as suggested I don't run into issues
        return;
    }

    /**
     * Clears all forum values
     */
    private void clearForm() {
        setGivenName("");
        setSurName("");
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }
}
