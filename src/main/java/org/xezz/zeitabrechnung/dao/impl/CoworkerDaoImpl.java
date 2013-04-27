package org.xezz.zeitabrechnung.dao.impl;

import org.xezz.zeitabrechnung.dao.CoworkerDao;
import org.xezz.zeitabrechnung.model.Coworker;

import java.util.List;

/**
 * User: Xezz
 * Date: 26.04.13
 * Time: 17:15
 */
public class CoworkerDaoImpl implements CoworkerDao {
    @Override
    public void addCoworker(Coworker coworker) {
        // FIXME: Implement this method

        /*
        Implementation is something like:
            em.persist(coworker);
         */
    }

    @Override
    public List<Coworker> findAllCoworkers() {
        return null;  // FIXME: Implement this method
    }
}
