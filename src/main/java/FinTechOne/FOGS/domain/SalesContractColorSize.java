package FinTechOne.FOGS.domain;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"scDetailId","colorWay","size"}, name = "UKey_SCDetail")})
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(callSuper=false, exclude = "salesContractDetail")
public class SalesContractColorSize extends EntityIdKeyBase {
    @ManyToOne
    @JoinColumn(name="scDetailId")
    private SalesContractDetail salesContractDetail;
    @NotNull
    @NotBlank
    @Size(max=100)
    private String colorWay;
    @NotNull
    @NotBlank
    @Size(max=10)
    private String size;
    @NotNull
    private int quantity;

}
