package org.xezz.zeitabrechnung.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xezz.zeitabrechnung.model.Coworker;
import org.xezz.zeitabrechnung.model.Project;
import org.xezz.zeitabrechnung.model.Timeframe;
import org.xezz.zeitabrechnung.repositories.TimeframeRepository;
import org.xezz.zeitabrechnung.services.TimeframeService;

import java.util.Date;
import java.util.List;

/**
 * User: Xezz
 * Date: 29.04.13
 * Time: 15:55
 * Implementation of the TimeframeService with Spring repository functionality
 */
@Service
public class TimeframeServiceImpl implements TimeframeService {

    @Autowired
    TimeframeRepository repo;

    @Override
    public List<Timeframe> timeframesForCoworker(Coworker coworker) {
        return repo.findByCoworker(coworker);
    }

    @Override
    public List<Timeframe> timeframesForProject(Project project) {
        return repo.findByProject(project);
    }

    @Override
    public void addNewTimeframe(Coworker c, Project p, Date startTime, Date endTime) {
        Timeframe timeframe = new Timeframe();
        timeframe.setCoworker(c);
        timeframe.setProject(p);
        timeframe.setStartTime(startTime);
        timeframe.setEndTime(endTime);
        repo.save(timeframe);
    }

    @Override
    public Timeframe getTimeFrameById(Long id) {
        return repo.findOne(id);
    }

    public TimeframeRepository getRepo() {
        return repo;
    }

    public void setRepo(TimeframeRepository repo) {
        this.repo = repo;
    }
}
