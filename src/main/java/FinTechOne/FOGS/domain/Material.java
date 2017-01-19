package FinTechOne.FOGS.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Data
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "code", name = "UKey_Material")})
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(callSuper=false)
public class Material extends EntityIdKeyBase {

    private static final long serialVersionUID = -2914831514227633961L;

    @NotNull
    @NotBlank
    @Size(max=10)
    private String code;
    @NotNull
    @NotBlank
    @Size(max=50)
    private String name;
    @NotNull
    @NotBlank
    @Size(max=10)
    private String type;
    @Size(max=200)
    private String description;
    @NotNull
    @NotBlank
    @Size(max=10)
    private String unit;
    @NotNull
    @Column(precision = 10, scale = 2)
    private BigDecimal unitPrice;
}
