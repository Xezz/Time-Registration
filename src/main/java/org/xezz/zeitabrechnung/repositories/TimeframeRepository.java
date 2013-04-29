package org.xezz.zeitabrechnung.repositories;

import org.springframework.data.repository.CrudRepository;
import org.xezz.zeitabrechnung.model.Timeframe;

/**
 * User: Xezz
 * Date: 27.04.13
 * Time: 22:07
 */
public interface TimeframeRepository extends CrudRepository<Timeframe, Long> {
}
