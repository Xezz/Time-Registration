package org.xezz.zeitabrechnung.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.xezz.zeitabrechnung.model.Coworker;
import org.xezz.zeitabrechnung.model.Project;
import org.xezz.zeitabrechnung.model.Timeframe;
import org.xezz.zeitabrechnung.repositories.CoworkerRepository;
import org.xezz.zeitabrechnung.services.CoworkerService;

import java.util.List;

/**
 * User: Xezz
 * Date: 29.04.13
 * Time: 16:02
 */
public class CoworkerServiceImpl implements CoworkerService  {

    @Autowired
    CoworkerRepository repo;

    @Override
    public List<Coworker> coworkersAll() {
        return repo.findAllCoworkers();
    }

    @Override
    public List<Coworker> coworkersByFirstName(String firstName) {
        return repo.findByFirstName(firstName);
    }

    @Override
    public List<Coworker> coworkersByLastName(String lastName) {
        return repo.findByLastName(lastName);
    }

    @Override
    public List<Coworker> coworkersByFirstAndLastName(String firstName, String lastName) {
        return repo.findByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public List<Coworker> coworkersByProject(Project p) {
        return repo.findCoworkersByProject(p);
    }

    @Override
    public Coworker coworkerById(Long id) {
        return repo.findOne(id);
    }

    @Override
    public Coworker coworkerByTimeFrame(Timeframe timeframe) {
        return timeframe.getCoworker();
    }

    @Override
    public Coworker addNewCoworker(Coworker coworker) {
        return repo.save(coworker);
    }

    @Override
    public Coworker updateCoworker(Coworker coworker) {
        if (repo.exists(coworker.getCoworkerId())) {
            return repo.save(coworker);
        } else {
            return null;
        }
    }
}
