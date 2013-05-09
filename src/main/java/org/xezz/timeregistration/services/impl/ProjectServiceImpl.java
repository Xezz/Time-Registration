package org.xezz.timeregistration.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.xezz.timeregistration.model.Customer;
import org.xezz.timeregistration.model.Project;
import org.xezz.timeregistration.model.Timeframe;
import org.xezz.timeregistration.repositories.ProjectRepository;
import org.xezz.timeregistration.services.ProjectService;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Xezz
 * Date: 05.05.13
 * Time: 11:14
 */
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectRepository repo;

    @Override
    public List<Project> getAll() {
        final Iterable<Project> all = repo.findAll();
        List<Project> retVal = new ArrayList<Project>();
        for (Project p : all) {
            retVal.add(p);
        }
        return retVal;
    }

    @Override
    public List<Project> getByName(String name) {
        return repo.findByName(name);
    }

    @Override
    public List<Project> getByCustomer(Customer c) {
        return repo.findByCustomer(c);
    }

    @Override
    public Project getByTimeFrame(Timeframe t) {
        return repo.findProjectByTimeframe(t);
    }

    @Override
    public Project getById(Long id) {
        return repo.findOne(id);
    }

    @Override
    public Project addNewProject(Project p) {
        return repo.save(p);
    }

    @Override
    public Project updateProject(Project p) {
        // TODO: update or (as of right now) also safe (aka against REST)
        return repo.save(p);
    }
}
