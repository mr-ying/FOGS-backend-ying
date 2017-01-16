package FinTechOne.FOGS.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EmbeddedId;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class EntityHistoryBase implements Serializable {
    @EmbeddedId
    private EntityHistoryBasePK pk;
    private Long version;
//    private Long createdBy;
    private String createdBy;
    private Date createdDate;
//    private Long lastModifiedBy;
    private String lastModifiedBy;
    @CreatedDate
    private Date historyCreatedDate;
    @CreatedBy
    private Long historyCreatedBy;


    public EntityHistoryBase(EntityBase entity){
        this.pk = new EntityHistoryBasePK();
//        this.pk.setId(entity.getId());
        this.version = entity.getVersion();
        this.createdBy = entity.getCreatedBy();
        this.createdDate = entity.getCreatedDate();
        this.lastModifiedBy = entity.getLastModifiedBy();
        this.pk.setLastModifiedDate(entity.getLastModifiedDate());
    }
}
