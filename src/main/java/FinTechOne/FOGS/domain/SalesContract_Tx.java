package FinTechOne.FOGS.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Data
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "scNum", name = "UKey_SC_TX")})
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(callSuper=false)
public class SalesContract_Tx extends SalesContractBase {
}
