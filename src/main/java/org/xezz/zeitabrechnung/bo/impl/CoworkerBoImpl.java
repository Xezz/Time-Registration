package org.xezz.zeitabrechnung.bo.impl;

import org.xezz.zeitabrechnung.bo.CoworkerBo;
import org.xezz.zeitabrechnung.repositories.CoworkerRepository;
import org.xezz.zeitabrechnung.model.Coworker;

import java.util.Collections;
import java.util.List;

/**
 * User: Xezz
 * Date: 26.04.13
 * Time: 17:07
 */
public class CoworkerBoImpl implements CoworkerBo {
    CoworkerRepository coworkerDao;

    public CoworkerBoImpl(CoworkerRepository coworkerDao) {
        this.coworkerDao = coworkerDao;
    }

    @Override
    public void addCoworker(Coworker coworker) {
        //coworkerDao.addCoworker(coworker);
    }

    @Override
    public List<Coworker> findAllCoworkers() {
        return Collections.<Coworker>emptyList();
        //coworkerDao.findAllCoworkers();
    }
}
