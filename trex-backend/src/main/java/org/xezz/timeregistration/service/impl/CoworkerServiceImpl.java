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
import org.xezz.timeregistration.model.Project;
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
@Transactional
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
        Iterable<Coworker> coworkersByProject = new ArrayList<Coworker>();
        if (p != null) {
            final Project project = projectRepository.findOne(p.getProjectId());
            if (project != null) {
                coworkersByProject = coworkerRepository.findCoworkersByProject(project);
            }
        }
        final List<CoworkerDAO> daoList = new ArrayList<CoworkerDAO>();
        for (Coworker c : coworkersByProject) {
            daoList.add(new CoworkerDAO(c));
        }
        return daoList;
    }

    @Override
    public CoworkerDAO getById(Long id) {
        Coworker coworker = coworkerRepository.findOne(id);
        return coworker != null ? new CoworkerDAO(coworker) : null;
    }

    @Override
    public CoworkerDAO getByTimeFrame(TimeSpanDAO timeSpanDAO) {
        return timeSpanDAO != null ? getById(timeSpanDAO.getCoworkerId()) : null;
    }

    @Override
    @Transactional
    public CoworkerDAO addNew(CoworkerDAO coworkerDAO) {
        Coworker saved = null;
         if(coworkerDAO != null) {
             saved = coworkerRepository.save(new Coworker(coworkerDAO));
         }
        return saved != null ? new CoworkerDAO(saved) : null;
    }

    @Override
    @Transactional
    public CoworkerDAO update(CoworkerDAO coworkerDAO) {
        // FIXME: Throw exception if it does not exist?
        return coworkerDAO != null && coworkerRepository.exists(coworkerDAO.getCoworkerId()) ? addNew(coworkerDAO) : null;
    }

    @Override
    public void delete(CoworkerDAO coworkerDAO) {
        if (coworkerDAO != null) {
            coworkerRepository.delete(new Coworker(coworkerDAO));
        }
    }
}
