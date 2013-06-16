package org.xezz.timeregistration.repository;

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
    Iterable<TimeSpan> findByProject(Project project);

    Iterable<TimeSpan> findByCoworker(Coworker coworker);
}
