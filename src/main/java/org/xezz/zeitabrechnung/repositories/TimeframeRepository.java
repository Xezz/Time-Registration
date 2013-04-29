package org.xezz.zeitabrechnung.repositories;

import org.springframework.data.repository.CrudRepository;
import org.xezz.zeitabrechnung.model.Coworker;
import org.xezz.zeitabrechnung.model.Project;
import org.xezz.zeitabrechnung.model.Timeframe;

import java.util.List;

/**
 * User: Xezz
 * Date: 27.04.13
 * Time: 22:07
 */
public interface TimeframeRepository extends CrudRepository<Timeframe, Long> {
    public List<Timeframe> findByProject(Project project);
    public List<Timeframe> findByCoworker(Coworker coworker);
}
