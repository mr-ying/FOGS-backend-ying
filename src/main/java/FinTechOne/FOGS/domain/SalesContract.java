package FinTechOne.FOGS.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "scNum", name = "UKey_SC")})
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(callSuper=false)
//@Audited
public class SalesContract extends SalesContractBase {
    @OneToMany(mappedBy = "salesContract", cascade = CascadeType.ALL)
    private Set<SalesContractDetail> salesContractDetails;
}
