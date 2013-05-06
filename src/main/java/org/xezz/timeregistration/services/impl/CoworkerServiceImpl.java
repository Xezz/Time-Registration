package org.xezz.timeregistration.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xezz.timeregistration.model.Coworker;
import org.xezz.timeregistration.model.Project;
import org.xezz.timeregistration.model.Timeframe;
import org.xezz.timeregistration.repositories.CoworkerRepository;
import org.xezz.timeregistration.services.CoworkerService;

import java.util.List;

/**
 * User: Xezz
 * Date: 29.04.13
 * Time: 16:02
 */
@Service
public class CoworkerServiceImpl implements CoworkerService  {
    Logger logger = LoggerFactory.getLogger(this.getClass());

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
        logger.info("Trying to find coworker with id: " + id);
        final Coworker one = repo.findOne(id);
        logger.info("Found one?: " + (one != null));
        return one;
    }

    @Override
    public Coworker coworkerByTimeFrame(Timeframe timeframe) {
        return timeframe.getCoworker();
    }

    @Override
    public Coworker addNewCoworker(Coworker coworker) {
        logger.info("Service saving coworker: " + coworker.getFirstName() + " " + coworker.getLastName());
        final Coworker save = repo.save(coworker);
        logger.info("Service saved coworker, is it != null? " + (save != null));
        return save;
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
