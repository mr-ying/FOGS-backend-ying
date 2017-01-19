package FinTechOne.FOGS.domain;

import FinTechOne.FOGS.validator.hibernate.CurrencyCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "currency", name = "UKey_Currency")})
@EntityListeners({AuditingEntityListener.class})
@EqualsAndHashCode(callSuper=false)
public class Currency extends EntityIdKeyBase {

    private static final long serialVersionUID = -3184347637020384668L;

    @NotNull
    @NotBlank
    @CurrencyCode
    @Size(max=3)
    private String currency;
    @NotNull
    @NotBlank
    @Size(max=40)
    private String name;

}
