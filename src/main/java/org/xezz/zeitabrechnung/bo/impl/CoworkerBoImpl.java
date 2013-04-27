package org.xezz.zeitabrechnung.bo.impl;

import org.xezz.zeitabrechnung.bo.CoworkerBo;
import org.xezz.zeitabrechnung.dao.CoworkerDao;
import org.xezz.zeitabrechnung.model.Coworker;

import java.util.List;

/**
 * User: Xezz
 * Date: 26.04.13
 * Time: 17:07
 */
public class CoworkerBoImpl implements CoworkerBo {
    CoworkerDao coworkerDao;

    public CoworkerBoImpl(CoworkerDao coworkerDao) {
        this.coworkerDao = coworkerDao;
    }

    @Override
    public void addCoworker(Coworker coworker) {
        coworkerDao.addCoworker(coworker);
    }

    @Override
    public List<Coworker> findAllCoworkers() {
        return coworkerDao.findAllCoworkers();
    }
}
