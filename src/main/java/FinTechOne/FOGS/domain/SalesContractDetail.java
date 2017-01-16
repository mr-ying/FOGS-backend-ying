package FinTechOne.FOGS.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Data
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"scId","seqNum"}, name = "UKey_SC")})
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(callSuper=false, exclude = "salesContract")
public class SalesContractDetail extends EntityIdKeyBase {

    @ManyToOne
    @JoinColumn(name="scId")
    private SalesContract salesContract;
    @NotNull
    private int seqNum;
    @NotNull
    @NotBlank
    @Size(max=20)
    private String poNum;
    @OneToMany(mappedBy = "salesContractDetail", cascade = CascadeType.ALL)
    private Set<SalesContractColorSize> salesContractColorSizes;
}
