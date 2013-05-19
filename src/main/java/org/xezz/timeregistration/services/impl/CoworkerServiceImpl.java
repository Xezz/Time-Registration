package org.xezz.timeregistration.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.xezz.timeregistration.model.Coworker;
import org.xezz.timeregistration.model.Project;
import org.xezz.timeregistration.model.TimeSpan;
import org.xezz.timeregistration.repositories.CoworkerRepository;
import org.xezz.timeregistration.services.CoworkerService;

/**
 * User: Xezz
 * Date: 29.04.13
 * Time: 16:02
 */
@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class CoworkerServiceImpl implements CoworkerService  {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CoworkerRepository repo;

    @Override
    public Iterable<Coworker> coworkersAll() {
        return repo.findAllCoworkers();
    }

    @Override
    public Iterable<Coworker> coworkersByFirstName(String firstName) {
        return repo.findByFirstName(firstName);
    }

    @Override
    public Iterable<Coworker> coworkersByLastName(String lastName) {
        return repo.findByLastName(lastName);
    }

    @Override
    public Iterable<Coworker> coworkersByFirstAndLastName(String firstName, String lastName) {
        return repo.findByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public Iterable<Coworker> coworkersByProject(Project p) {
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
    public Coworker coworkerByTimeFrame(TimeSpan timeSpan) {
        return timeSpan.getCoworker();
    }

    @Override
    @Transactional
    public Coworker addNewCoworker(Coworker coworker) {
        logger.info("Service saving coworker: " + coworker.getFirstName() + " " + coworker.getLastName());
        final Coworker save = repo.save(coworker);
        logger.info("Service saved coworker, is it != null? " + (save != null));
        logger.info("First name: " + save.getFirstName() + " Last name: " + save.getLastName() + " Date created: " + save.getCreationDate() + " id: " + save.getCoworkerId());
        return save;
    }

    @Override
    @Transactional
    public Coworker updateCoworker(Coworker coworker) {
        if (repo.exists(coworker.getCoworkerId())) {
            return repo.save(coworker);
        } else {
            return null;
        }
    }
}
