package FinTechOne.FOGS.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(callSuper=false)
@Audited
public class EntityIdKeyBase extends EntityBase {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long idntty;

}
