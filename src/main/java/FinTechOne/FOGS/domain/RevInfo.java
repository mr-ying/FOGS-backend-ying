package FinTechOne.FOGS.domain;

import FinTechOne.FOGS.auditing.ERPRevisionListener;
import lombok.Data;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@RevisionEntity(ERPRevisionListener.class)
@Entity
@Data
@Table
public class RevInfo {
    @Id
    @GeneratedValue
    @RevisionNumber
    private long id;

    @RevisionTimestamp
    private long timestamp;

//    private Long user;
    private String user;
}
