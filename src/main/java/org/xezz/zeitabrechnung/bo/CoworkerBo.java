package org.xezz.zeitabrechnung.bo;

import org.xezz.zeitabrechnung.model.Coworker;

import java.util.List;

/**
 * User: Xezz
 * Date: 26.04.13
 * Time: 16:55
 */
public interface CoworkerBo {
    void addCoworker(Coworker coworker);
    List<Coworker> findAllCoworkers();
}
