package FinTechOne.FOGS.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

//import javax.persistence.*;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Audited
public class EntityTableKeyBase {
    @Id
    @GeneratedValue(strategy= GenerationType.TABLE, generator = "tx_master")
    @GenericGenerator(name = "tx_master", strategy = "org.hibernate.id.enhanced.TableGenerator",
        parameters = {
            @Parameter(name = "table_name", value = "SEQUENCES"),
            @Parameter(name = "value_column_name", value = "NEXTVALUE"),
            @Parameter(name = "segment_column_name", value = "NAME"),
            @Parameter(name = "segment_value", value = "tx_master"),
            @Parameter(name = "increment_size", value = "1")
        }
    )
    private Long id;

    @Version
    @JsonIgnore
    private Long version;


    @CreatedBy
    @Size(max=10)
    private String createdBy;

    @CreatedDate
    private Date createdDate;

    @LastModifiedBy
    @Size(max=10)
    private String lastModifiedBy;

    @LastModifiedDate
    private Date lastModifiedDate;

}
