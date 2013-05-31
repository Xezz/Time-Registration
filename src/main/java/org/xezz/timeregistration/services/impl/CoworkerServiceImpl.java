package org.xezz.timeregistration.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.xezz.timeregistration.dao.CoworkerDAO;
import org.xezz.timeregistration.dao.ProjectDAO;
import org.xezz.timeregistration.dao.TimeSpanDAO;
import org.xezz.timeregistration.model.Coworker;
import org.xezz.timeregistration.repositories.CoworkerRepository;
import org.xezz.timeregistration.repositories.ProjectRepository;
import org.xezz.timeregistration.services.CoworkerService;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Xezz
 * Date: 29.04.13
 * Time: 16:02
 */
@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class CoworkerServiceImpl implements CoworkerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CoworkerServiceImpl.class);

    @Autowired
    CoworkerRepository coworkerRepository;
    @Autowired
    ProjectRepository projectRepository;

   // FIXME: SO this for a real way to handle it
    @Override
    public Iterable<CoworkerDAO> coworkersAll() {
        final Iterable<Coworker> allCoworkers = coworkerRepository.findAllCoworkers();
        final List<CoworkerDAO> allDao = new ArrayList<CoworkerDAO>();
        for (Coworker c : allCoworkers) {
            allDao.add(new CoworkerDAO(c));
        }
        return allDao;
    }

    @Override
    public Iterable<CoworkerDAO> coworkersByFirstName(String firstName) {
        final Iterable<Coworker> byFirstName = coworkerRepository.findByFirstName(firstName);
        final List<CoworkerDAO> daoList = new ArrayList<CoworkerDAO>();
        for (Coworker c : byFirstName) {
            daoList.add(new CoworkerDAO(c));
        }
        return daoList;
    }

    @Override
    public Iterable<CoworkerDAO> coworkersByLastName(String lastName) {
        final Iterable<Coworker> byLastName = coworkerRepository.findByLastName(lastName);
        final List<CoworkerDAO> daoList = new ArrayList<CoworkerDAO>();
        for (Coworker c : byLastName) {
            daoList.add(new CoworkerDAO(c));
        }
        return daoList;
    }

    @Override
    public Iterable<CoworkerDAO> coworkersByFirstAndLastName(String firstName, String lastName) {
        final Iterable<Coworker> byFirstNameAndLastName = coworkerRepository.findByFirstNameAndLastName(firstName, lastName);
        final List<CoworkerDAO> daoList = new ArrayList<CoworkerDAO>();
        for (Coworker c : byFirstNameAndLastName) {
            daoList.add(new CoworkerDAO(c));
        }
        return daoList;
    }

    @Override
    public Iterable<CoworkerDAO> coworkersByProject(ProjectDAO p) {
        final Iterable<Coworker> coworkersByProject = coworkerRepository.findCoworkersByProject(projectRepository.findOne(p.getProjectId()));
        final List<CoworkerDAO> daoList = new ArrayList<CoworkerDAO>();
        for (Coworker c : coworkersByProject) {
            daoList.add(new CoworkerDAO(c));
        }
        return daoList;
    }

    @Override
    public CoworkerDAO coworkerById(Long id) {
        LOGGER.info("Trying to find coworker with id: " + id);
        final CoworkerDAO one = new CoworkerDAO(coworkerRepository.findOne(id));
        LOGGER.info("Found one?: " + (one != null));
        return one;
    }

    @Override
    public CoworkerDAO coworkerByTimeFrame(TimeSpanDAO timeSpanDAO) {
        return coworkerById(timeSpanDAO.getCoworkerId());
    }

    @Override
    @Transactional
    public CoworkerDAO addNewCoworker(CoworkerDAO coworkerDAO) {
        LOGGER.info("Service saving coworker: " + coworkerDAO.getFirstName() + " " + coworkerDAO.getLastName());
        final CoworkerDAO save = new CoworkerDAO(coworkerRepository.save(new Coworker(coworkerDAO)));
        LOGGER.info("Service saved coworker, is it != null? " + (save != null));
        LOGGER.info("First name: " + save.getFirstName() + " Last name: " + save.getLastName() + " Date created: " + save.getCreationDate() + " id: " + save.getCoworkerId());
        return save;
    }

    @Override
    @Transactional
    public CoworkerDAO updateCoworker(CoworkerDAO coworkerDAO) {
        Coworker coworker = coworkerRepository.findOne(coworkerDAO.getCoworkerId());
        if (coworker != null) {
            coworker = new Coworker(coworkerDAO);
            return new CoworkerDAO(coworkerRepository.save(coworker));
        } else {
            return null;
        }
    }
}
