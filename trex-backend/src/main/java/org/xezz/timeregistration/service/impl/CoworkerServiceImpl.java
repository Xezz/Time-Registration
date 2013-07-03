package org.xezz.timeregistration.service.impl;

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
import org.xezz.timeregistration.repository.CoworkerRepository;
import org.xezz.timeregistration.repository.ProjectRepository;
import org.xezz.timeregistration.service.CoworkerService;

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
    public Iterable<CoworkerDAO> getAll() {
        final Iterable<Coworker> allCoworkers = coworkerRepository.findAllCoworkers();
        final List<CoworkerDAO> allDao = new ArrayList<CoworkerDAO>();
        for (Coworker c : allCoworkers) {
            allDao.add(new CoworkerDAO(c));
        }
        return allDao;
    }

    @Override
    public Iterable<CoworkerDAO> getByFirstName(String firstName) {
        final Iterable<Coworker> byFirstName = coworkerRepository.findByFirstName(firstName);
        final List<CoworkerDAO> daoList = new ArrayList<CoworkerDAO>();
        for (Coworker c : byFirstName) {
            daoList.add(new CoworkerDAO(c));
        }
        return daoList;
    }

    @Override
    public Iterable<CoworkerDAO> getByLastName(String lastName) {
        final Iterable<Coworker> byLastName = coworkerRepository.findByLastName(lastName);
        final List<CoworkerDAO> daoList = new ArrayList<CoworkerDAO>();
        for (Coworker c : byLastName) {
            daoList.add(new CoworkerDAO(c));
        }
        return daoList;
    }

    @Override
    public Iterable<CoworkerDAO> getByFirstAndLastName(String firstName, String lastName) {
        final Iterable<Coworker> byFirstNameAndLastName = coworkerRepository.findByFirstNameAndLastName(firstName, lastName);
        final List<CoworkerDAO> daoList = new ArrayList<CoworkerDAO>();
        for (Coworker c : byFirstNameAndLastName) {
            daoList.add(new CoworkerDAO(c));
        }
        return daoList;
    }

    @Override
    public Iterable<CoworkerDAO> getByProject(ProjectDAO p) {
        final Iterable<Coworker> coworkersByProject = coworkerRepository.findCoworkersByProject(projectRepository.findOne(p.getProjectId()));
        final List<CoworkerDAO> daoList = new ArrayList<CoworkerDAO>();
        for (Coworker c : coworkersByProject) {
            daoList.add(new CoworkerDAO(c));
        }
        return daoList;
    }

    @Override
    public CoworkerDAO getById(Long id) {
        LOGGER.debug("Trying to find coworker with id: " + id);
        Coworker coworker = coworkerRepository.findOne(id);
        final CoworkerDAO one = coworker != null ? new CoworkerDAO(coworker) : null;
        LOGGER.debug("Found one?: " + (one != null));
        return one;
    }

    @Override
    public CoworkerDAO getByTimeFrame(TimeSpanDAO timeSpanDAO) {
        return getById(timeSpanDAO.getCoworkerId());
    }

    @Override
    @Transactional
    public CoworkerDAO addNew(CoworkerDAO coworkerDAO) {
        LOGGER.debug("Service saving coworker: " + coworkerDAO.getFirstName() + " " + coworkerDAO.getLastName());
        Coworker coworker = coworkerRepository.save(new Coworker(coworkerDAO));
        final CoworkerDAO save = coworker != null ? new CoworkerDAO(coworker) : null;
        LOGGER.debug("Service saved coworker, is it != null? " + (save != null));
        if (save != null) {
            LOGGER.debug("First name: " + save.getFirstName() + " Last name: " + save.getLastName() + " Date created: " + save.getCreationDate() + " id: " + save.getCoworkerId());
        }
        return save;
    }

    @Override
    @Transactional
    public CoworkerDAO update(CoworkerDAO coworkerDAO) {
        Coworker coworker = coworkerRepository.findOne(coworkerDAO.getCoworkerId());
        if (coworker != null) {
            coworker.updateFromDao(coworkerDAO);
            return new CoworkerDAO(coworkerRepository.save(coworker));
        } else {
            return null;
        }
    }

    @Override
    public void delete(CoworkerDAO coworkerDAO) {
        final Coworker coworker = coworkerRepository.findOne(coworkerDAO.getCoworkerId());
        if (coworker != null) {
            coworkerRepository.delete(coworker);
        }
    }
}
