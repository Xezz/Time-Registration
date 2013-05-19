package org.xezz.timeregistration.repositories;

import org.springframework.data.repository.CrudRepository;
import org.xezz.timeregistration.model.Coworker;
import org.xezz.timeregistration.model.Project;
import org.xezz.timeregistration.model.TimeSpan;

/**
 * User: Xezz
 * Date: 27.04.13
 * Time: 22:07
 */
public interface TimeSpanRepository extends CrudRepository<TimeSpan, Long> {
    public Iterable<TimeSpan> findByProject(Project project);

    public Iterable<TimeSpan> findByCoworker(Coworker coworker);
}
