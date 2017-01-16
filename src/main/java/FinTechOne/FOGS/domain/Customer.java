package FinTechOne.FOGS.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "customerId", name = "UKey_Customer")})
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(callSuper=false)
@Audited
public class Customer extends EntityIdKeyBase {
    @NotNull
    @NotBlank
    @Size(max=10)
    private String customerId;
    @NotNull
    @NotBlank
    @Size(max=40)
    private String name;
    @NotNull
    @NotBlank
    @Size(max=200)
    private String address;
    @Size(max=200)
    private String addressChinese;
}
