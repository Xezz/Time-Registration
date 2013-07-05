package org.xezz.timeregistration.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xezz.timeregistration.dao.CoworkerDAO;
import org.xezz.timeregistration.dao.ProjectDAO;
import org.xezz.timeregistration.dao.TimeSpanDAO;
import org.xezz.timeregistration.model.Coworker;
import org.xezz.timeregistration.model.Project;
import org.xezz.timeregistration.model.TimeSpan;
import org.xezz.timeregistration.repository.CoworkerRepository;
import org.xezz.timeregistration.repository.ProjectRepository;
import org.xezz.timeregistration.repository.TimeSpanRepository;
import org.xezz.timeregistration.service.TimeSpanService;

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
    public Iterable<TimeSpanDAO> getByCoworker(CoworkerDAO coworkerDAO) {
        Iterable<TimeSpan> byCoworker = new ArrayList<TimeSpan>();
        if (coworkerDAO != null) {
            final Coworker coworker = coworkerRepository.findOne(coworkerDAO.getCoworkerId());
            if (coworker != null) {
                byCoworker = repo.findByCoworker(coworker);
            }
        }
        return getTimeSpanDAOs(byCoworker);
    }

    @Override
    public Iterable<TimeSpanDAO> getByProject(ProjectDAO projectDAO) {
        Iterable<TimeSpan> byProject = new ArrayList<TimeSpan>();
        if (projectDAO!= null) {
            final Project project = projectRepository.findOne(projectDAO.getProjectId());
            if (project != null) {
                byProject = repo.findByProject(project);
            }
        }
        return getTimeSpanDAOs(byProject);
    }

    @Override
    public TimeSpanDAO getById(Long id) {
        final TimeSpan entity = repo.findOne(id);
        return entity != null ? new TimeSpanDAO(entity) : null;
    }


    @Transactional
    @Override
    public TimeSpanDAO addNew(TimeSpanDAO timeSpanDAO) {
        return timeSpanDAO != null ? new TimeSpanDAO(repo.save(new TimeSpan(timeSpanDAO))) : null;
    }

    @Transactional
    @Override
    public TimeSpanDAO update(TimeSpanDAO timeSpanDAO) {
        return timeSpanDAO != null ? new TimeSpanDAO(repo.save(new TimeSpan(timeSpanDAO))) : null;
    }

    @Override
    public Iterable<TimeSpanDAO> getAll() {
        return getTimeSpanDAOs(repo.findAll());
    }

    @Override
    public void delete(TimeSpanDAO timeSpanDAO) {
        TimeSpan timeSpan = repo.findOne(timeSpanDAO.getTimeSpanId());
        if (timeSpan != null) {
            repo.delete(timeSpan);
        }
    }

    /**
     * Helper method to convert TimeSpan's to TimeSpanDAO's
     *
     * @param timeSpanList all TimeSpan's to convert
     * @return resulting Iterable of TimeSpanDAO's
     */
    private Iterable<TimeSpanDAO> getTimeSpanDAOs(Iterable<TimeSpan> timeSpanList) {
        final List<TimeSpanDAO> daoList = new ArrayList<TimeSpanDAO>();
        for (TimeSpan t : timeSpanList) {
            daoList.add(new TimeSpanDAO(t));
        }
        return daoList;
    }
}
