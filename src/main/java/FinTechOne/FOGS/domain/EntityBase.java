package FinTechOne.FOGS.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Audited
@EqualsAndHashCode(callSuper = false)
public class EntityBase extends ResourceSupport {

    @Version
    @JsonIgnore
    private Long version;

//    @CreatedBy
//    @JoinColumn(name = "createdBy")
//    @ManyToOne
//    private Entitlement createdBy;

    @CreatedBy
//    private Long createdBy;
    @Size(max=10)
    private String createdBy;

    @CreatedDate
    private Date createdDate;

//    @LastModifiedBy
//    @JoinColumn(name = "lastModifiedBy")
//    @ManyToOne
//    private Entitlement lastModifiedBy;

    @LastModifiedBy
//    private Long lastModifiedBy;
    @Size(max=10)
    private String lastModifiedBy;

    @LastModifiedDate
    private Date lastModifiedDate;

}
