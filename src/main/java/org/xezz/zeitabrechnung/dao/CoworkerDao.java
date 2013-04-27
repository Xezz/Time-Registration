package org.xezz.zeitabrechnung.dao;

import org.xezz.zeitabrechnung.model.Coworker;

import java.util.List;

/**
 * User: Xezz
 * Date: 26.04.13
 * Time: 16:56
 */
public interface CoworkerDao {
    void addCoworker(Coworker coworker);
    List<Coworker> findAllCoworkers();
}
