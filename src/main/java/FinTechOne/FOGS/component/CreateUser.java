package FinTechOne.FOGS.component;

import FinTechOne.FOGS.domain.*;
import FinTechOne.FOGS.repository.EntitlementRepository;
import FinTechOne.FOGS.repository.SalesContractRepository;
import FinTechOne.FOGS.repository.SalesContract_TxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;

@Component
public class CreateUser implements CommandLineRunner {

    private final EntitlementRepository users;

    @Autowired
    private SalesContractRepository salesContracts;

    @Autowired
    private SalesContract_TxRepository salesContract_Txs;

    @Autowired
    public CreateUser(EntitlementRepository users) {
        this.users = users;
    }

    public void run(String... args) {
        if (users.count() == 0) {
            Entitlement user;
            user = new Entitlement();
            user.setUserId("admin");
            user.setName("Administration");
            user.setPassword("admin");
            user.setJobTitle("super user 1");
            user.setRole("Manager");
            user.setEmail("admin@gmail.com");
            users.save(user);
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken("admin", "doesn't matter",
                            AuthorityUtils.createAuthorityList("ROLE_MANAGER")));
            SecurityContextHolder.clearContext();
            user = new Entitlement();
            user.setUserId("manager");
            user.setName("Manager");
            user.setPassword("manager");
            user.setJobTitle("super user 2");
            user.setRole("Manager");
            user.setEmail("manager@gmail.com");
            users.save(user);
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken("manager", "doesn't matter",
                            AuthorityUtils.createAuthorityList("ROLE_MANAGER")));
            SecurityContextHolder.clearContext();

            SalesContract sc = new SalesContract();
            sc.setScNum("SC00001");
            sc.setCustomerId("CS01");

            SalesContractDetail scDtl1 = new SalesContractDetail();
            scDtl1.setSalesContract(sc);
            scDtl1.setSeqNum(1);
            scDtl1.setPoNum("PO0000101");

            SalesContractColorSize scCS11 = new SalesContractColorSize();
            scCS11.setSalesContractDetail(scDtl1);
            scCS11.setColorWay("Ultramarine with grey sleeves");
            scCS11.setSize("XS");
            scCS11.setQuantity(200);

            SalesContractColorSize scCS12 = new SalesContractColorSize();
            scCS12.setSalesContractDetail(scDtl1);
            scCS12.setColorWay("Yellow blue");
            scCS12.setSize("M");
            scCS12.setQuantity(2500);

            LinkedHashSet<SalesContractColorSize> details1 = new LinkedHashSet<>();
            details1.add(scCS11);
            details1.add(scCS12);
            scDtl1.setSalesContractColorSizes(details1);

            SalesContractDetail scDtl2 = new SalesContractDetail();
            scDtl2.setSalesContract(sc);
            scDtl2.setSeqNum(2);
            scDtl2.setPoNum("PO0000102");

            SalesContractColorSize scCS21 = new SalesContractColorSize();
            scCS21.setSalesContractDetail(scDtl2);
            scCS21.setColorWay("Cerulean");
            scCS21.setSize("L");
            scCS21.setQuantity(2300);

            SalesContractColorSize scCS22 = new SalesContractColorSize();
            scCS22.setSalesContractDetail(scDtl2);
            scCS22.setColorWay("Yellow blue");
            scCS22.setSize("XL");
            scCS22.setQuantity(1400);

            LinkedHashSet<SalesContractColorSize> details2 = new LinkedHashSet<>();
            details2.add(scCS21);
            details2.add(scCS22);
            scDtl2.setSalesContractColorSizes(details2);

            SalesContractDetail scDtl3 = new SalesContractDetail();
            scDtl3.setSalesContract(sc);
            scDtl3.setSeqNum(3);
            scDtl3.setPoNum("PO0000103");

            SalesContractColorSize scCS31 = new SalesContractColorSize();
            scCS31.setSalesContractDetail(scDtl3);
            scCS31.setColorWay("Black");
            scCS31.setSize("S");
            scCS31.setQuantity(953);

            SalesContractColorSize scCS32 = new SalesContractColorSize();
            scCS32.setSalesContractDetail(scDtl3);
            scCS32.setColorWay("Yellow blue");
            scCS32.setSize("XXL");
            scCS32.setQuantity(1600);

            LinkedHashSet<SalesContractColorSize> details3 = new LinkedHashSet<>();
            details3.add(scCS31);
            details3.add(scCS32);
            scDtl3.setSalesContractColorSizes(details3);

            LinkedHashSet<SalesContractDetail> details = new LinkedHashSet<>();
            details.add(scDtl1);
            details.add(scDtl2);
            details.add(scDtl3);
            sc.setSalesContractDetails(details);
            salesContracts.save(sc);

            SalesContract_Tx sc_tx = new SalesContract_Tx();
            sc_tx.setScNum("SC00002");
            sc_tx.setCustomerId("CS02");
            salesContract_Txs.save(sc_tx);

        }
    }
}
