package org.xezz.zeitabrechnung.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * User: Xezz
 * Date: 26.04.13
 * Time: 17:50
 */
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long projectId;
    private String name;
    private String description;
    private Customer customer;
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Temporal(TemporalType.DATE)
    private Date lastUpdatedDate;
    @ManyToOne
    private List<Timeframe> timeframes;

    /**
     * Default Constructor to support DI and JPA
     */
    public Project() {}
}
