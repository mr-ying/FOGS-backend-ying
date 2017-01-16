package FinTechOne.FOGS.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@Table
@EqualsAndHashCode(callSuper=false)
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class CustomerHistory extends EntityHistoryBase {
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

    public CustomerHistory(Customer customer){
        super(customer);
        this.customerId = customer.getCustomerId();
        this.name = customer.getName();
        this.address = customer.getAddress();
        this.addressChinese = customer.getAddressChinese();
    }
}
