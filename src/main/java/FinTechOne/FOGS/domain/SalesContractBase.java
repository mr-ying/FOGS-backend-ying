package FinTechOne.FOGS.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.*;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Audited
@EqualsAndHashCode(callSuper=false)
public class SalesContractBase extends EntityBase {
    @Id
    @GeneratedValue(strategy= GenerationType.TABLE, generator = "SC")
    @GenericGenerator(name = "SC", strategy = "org.hibernate.id.enhanced.TableGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "table_name", value = "SEQUENCES"),
                    @org.hibernate.annotations.Parameter(name = "value_column_name", value = "NEXTVALUE"),
                    @org.hibernate.annotations.Parameter(name = "segment_column_name", value = "NAME"),
                    @org.hibernate.annotations.Parameter(name = "segment_value", value = "SC"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    private Long idntty;

    @NotNull
    @NotBlank
    @Size(max=20)
    private String scNum;
    @NotNull
    @NotBlank
    @Size(max=10)
    private String customerId;
}
