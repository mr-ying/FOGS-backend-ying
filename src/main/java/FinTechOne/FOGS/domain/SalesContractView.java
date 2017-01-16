package FinTechOne.FOGS.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
@Data
public class SalesContractView extends EntityIdKeyBase {
    private String scNum;
    private String customerId;
    private boolean master;
}
