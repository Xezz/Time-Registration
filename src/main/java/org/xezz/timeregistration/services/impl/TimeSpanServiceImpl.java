package org.xezz.timeregistration.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xezz.timeregistration.dao.CoworkerDAO;
import org.xezz.timeregistration.dao.ProjectDAO;
import org.xezz.timeregistration.dao.TimeSpanDAO;
import org.xezz.timeregistration.model.TimeSpan;
import org.xezz.timeregistration.repositories.CoworkerRepository;
import org.xezz.timeregistration.repositories.ProjectRepository;
import org.xezz.timeregistration.repositories.TimeSpanRepository;
import org.xezz.timeregistration.services.TimeSpanService;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Xezz
 * Date: 29.04.13
 * Time: 15:55
 * Implementation of the TimeSpanService with Spring repository functionality
 */
@Service
@Transactional
public class TimeSpanServiceImpl implements TimeSpanService {

    @Autowired
    TimeSpanRepository repo;
    @Autowired
    CoworkerRepository coworkerRepository;
    @Autowired
    ProjectRepository projectRepository;

    @Override
    public Iterable<TimeSpanDAO> timeSpansForCoworker(CoworkerDAO coworkerDAO) {
        final Iterable<TimeSpan> byCoworker = repo.findByCoworker(coworkerRepository.findOne(coworkerDAO.getCoworkerId()));
        final List<TimeSpanDAO> daoList = getTimeSpanDAOs(byCoworker);
        return daoList;
    }

    private List<TimeSpanDAO> getTimeSpanDAOs(Iterable<TimeSpan> timeSpanList) {
        final List<TimeSpanDAO> daoList = new ArrayList<TimeSpanDAO>();
        for (TimeSpan t : timeSpanList) {
            daoList.add(new TimeSpanDAO(t));
        }
        return daoList;
    }

    @Override
    public Iterable<TimeSpanDAO> timeSpansForProject(ProjectDAO projectDAO) {
        final Iterable<TimeSpan> byProject = repo.findByProject(projectRepository.findOne(projectDAO.getProjectId()));
        final List<TimeSpanDAO> daoList = getTimeSpanDAOs(byProject);
        return daoList;
    }


    @Override
    public TimeSpanDAO getTimeSpanById(Long id) {
        return new TimeSpanDAO(repo.findOne(id));
    }

    @Transactional
    @Override
    public TimeSpanDAO createNewTimeSpan(TimeSpanDAO timeSpanDAO) {
        return new TimeSpanDAO(repo.save(new TimeSpan(timeSpanDAO)));
    }

    // FIXME: Actually make the TimeSpan clone from TimeSpanDao?
    @Transactional
    @Override
    public TimeSpanDAO updateTimeSpan(TimeSpanDAO timeSpanDAO) {
        return new TimeSpanDAO(repo.save(new TimeSpan(timeSpanDAO)));
    }

    @Override
    public Iterable<TimeSpanDAO> findAllTimeSpans() {
        final Iterable<TimeSpan> all = repo.findAll();
        return getTimeSpanDAOs(all);
    }

    @Override
    public void deleteTimeSpan(TimeSpanDAO timeSpanDAO) {
        TimeSpan timeSpan = repo.findOne(timeSpanDAO.getTimeSpanId());
        if (timeSpan != null) {
            repo.delete(timeSpan);
        }
    }
}
