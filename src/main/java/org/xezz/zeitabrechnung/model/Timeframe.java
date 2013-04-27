package org.xezz.zeitabrechnung.model;

import javax.persistence.*;
import java.util.Date;

/**
 * User: Xezz
 * Date: 26.04.13
 * Time: 17:53
 */
@Entity
public class Timeframe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long timeframeId;
    private Project project;
    private Coworker coworker;
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Temporal(TemporalType.DATE)
    private Date lastUpdatedDate;

    @PreUpdate
    public void setCreationDate() {
        this.creationDate = new Date();
        this.lastUpdatedDate = (Date) creationDate.clone();
    }

    @PrePersist
    public void setLastUpdatedDate() {
        this.lastUpdatedDate = new Date();
    }
}
